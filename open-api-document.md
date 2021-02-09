# 推送平台开放文档



## 服务端API



### 消息推送

#### Sign值计算说明

签名Sign值是校验用户调用推送接口权限的必要值。

**签名算法描述如下：**

1. 将请求参数按参数名升序排序（当参数值为数组或集合时，需要对参数值内部进行升序排序）；
2. 按请求参数名及参数值相互连接组成一个字符串；
3. 将应用密钥分别添加到以上请求参数串的头部和尾部； 
4. 对该字符串进行MD5**（全部大写、32字符）**不可逆加密，MD5后的字符串即是这些请求参数对应的Sign值； 
5. 该签名值使用sign参数一起和其它请求参数一起发送给服务开放平台；
6. 其他注意事项：
   - 当参数类型为数组时候，拼接的参数串使用 "[]" 包括、元素之间 "," 隔开，示例：[array1,array2,……]；
   - 当参数类型为集合时候，拼接的参数串使用 "{}" 包括、key与value之间 "=" 连接、元素之间 "," 隔开，示例：{key1=value1,key2=value2,……}；
   - 在最终拼接得到请求参数串后，确保该参数串中无空格后再进行MD5加密；
   - 当非必填对象为空时，请使用空值而非null。

**Sign值的计算如下所示：**

```mathematica
Sign = Md5 ( secret + mapString + secret )
```

##### 字段说明

| 名称      | 类型   | 示例值                       | 描述                                                         |
| --------- | ------ | ---------------------------- | ------------------------------------------------------------ |
| secret    | String | 0032cbxxxxxxxxxxxxxxx15vUcYY | 应用对用的密钥值，长度48个字符。创建应用时自动生成，唯一，可重置。 |
| mapString | String | name李四…                    | 参数名及参数值拼接字符串。                                   |

##### 签名示例

###### 签名构建方法（Java）

```java
public static String buildSign(Map<String, Object> paramsMap, String secret) throws IOException {
    Set<String> keySet = paramsMap.keySet();
    List<String> paramNames = new ArrayList<String>(keySet);
	// 请求参数按参数名升序排序
    Collections.sort(paramNames);
    StringBuilder paramNameValue = new StringBuilder();
	// 拼接参数名及参数值
    for (String paramName : paramNames) {
        // 对象类型为数组时升序排序后使用Arrays.toString()方法
        if (paramsMap.get(paramName).getClass().isArray()){
            Object[] array = (Object[]) paramsMap.get(paramName);
            Arrays.sort(array);
            paramNameValue.append(paramName).append(Arrays.toString(array));
            continue;
        }
        // 对象类型为Map时按Key值升序排序
        if (paramsMap.get(paramName) instanceof Map){
            Map<String, Object> map = new TreeMap<>((HashMap<String, Object>) paramsMap.get(paramName));
            paramNameValue.append(paramName).append(map);
            continue;
        }
        paramNameValue.append(paramName).append(paramsMap.get(paramName));
    }
	// 拼接密钥
    String source = (secret + paramNameValue.toString() + secret).replaceAll(" ", "");
    return DigestUtils.md5DigestAsHex(source.getBytes(StandardCharsets.UTF_8)).toUpperCase();
}
```

###### 传入参数

```json
{
    "messageId": "6233e03e-c360-4632-afeb-7ce9287664b0",
    "requestTime": 1612833535152,
    "callBackUrl": "",
    "isCallBack": false,
    "appId": 1,
    "phoneNum": [
        "139588xxxxx", 
        "135875xxxxx"
    ],
    "templateId": 4,
    "vars": {
        "c": "cccc",
        "aa": 1,
        "a": "aaaa",
        "b": "bbbb"
    }
}
```

###### 返回

**secret密钥值：**

```mark
0032cb9ba6d64f14bbb831bb1dc06092HU4k6YzDT15vUcYY
```

**请求参数串：**

```markdown
0032cb9ba6d64f14bbb831bb1dc06092HU4k6YzDT15vUcYYappId1callBackUrlisCallBackfalsemessageIdae35e7e4-5e52-4c64-8a90-f60423b1e57aphoneNum[135875xxxxx,139588xxxxx]requestTime1612838032552templateId4vars{a=aaaa,aa=1,b=bbbb,c=cccc}0032cb9ba6d64f14bbb831bb1dc06092HU4k6YzDT15vUcYY
```

**生成的Sign值：**

```markdown
EFEA6EC973AB9003346DEA4B5A7B7F36
```

#### 回调说明

当callBackUrl参数不为空时，会调用回调地址并返回消息推送结果。

##### 请求方式

POST

##### 请求参数

| 名称    | 类型    | 示例值  | 描述                                           |
| ------- | ------- | ------- | ---------------------------------------------- |
| code    | Integer | 0       | 接口调用状态码，0表示成功，其余表示失败/异常。 |
| message | String  | success | 接口调用返回信息。                             |

##### 请求示例

```json
{
    "code": 32100006,
    "message": "模板变量不符合规范"
}
```



### 短信推送

调用本接口**发送短信通知。**

#### 基本信息

**请求方式：**POST

**请求地址：**`/api/v1/open/push/sms`

#### Body参数

| 名称        | 类型                | 是否必填 | 示例值                               | 描述                                             |
| ----------- | ------------------- | -------- | ------------------------------------ | ------------------------------------------------ |
| messageId   | String              | 是       | 2ebb14dd-1691-40c8-9078-32f97488aea3 | 通用唯一识别码，UUID。                           |
| appId       | Long                | 是       | 1L                                   | 应用唯一标识ID（不可修改），应用之间唯一。       |
| isCallBack  | Boolean             | 否       | false                                | 是否需要回调。true/false，默认为false。          |
| callBackUrl | String              | 否       | http://aaxxxaaa.com                  | 回调地址。                                       |
| requestTime | Timestamp           | 是       | 1612170463260                        | 发起请求时间戳。                                 |
| sign        | String              | 是       | 2c6e52xxxxxx1e2b0bf9                 | 签名sign值。                                     |
| phoneNum    | String[]            | 是       | [“139588xxxxx”, “135875xxxxx”,…]     | 短信推送手机号码数组，手机号应为标准可用手机号。 |
| templateId  | Long                | 是       | 1L                                   | 模板ID。                                         |
| vars        | Map<String, String> | 否       | {"a":"xxx", "b":"xxx", "c":"xxx", …} | 模版变量键值对。                                 |

#### 返回参数

| 名称    | 类型    | 示例值  | 描述                                           |
| ------- | ------- | ------- | ---------------------------------------------- |
| code    | Integer | 0       | 接口调用状态码，0表示成功，其余表示失败/异常。 |
| message | String  | success | 接口调用返回信息。                             |
| data    | Object  | null    | 调用接口返回参数，一般为null。                 |

#### 示例

##### **请求示例（HTTP）**

```http
POST http://host:port/api/v1/open/push/sms
```

##### 请求正文

```json
{
    "messageId": "44b87b64-a29c-4e5d-ac84-efcf9c2bff23",
    "appId": 3,
    "isCallBack": false,
    "callBackUrl": null,
    "requestTime": 1612158205420,
    "sign": "2c6e52xxxxxx1e2b0bf9",
    "phoneNum":[
        "13588162637"
    ],
    "templateId": 4,
    "vars":{
        "a": "518687"
    }
}
```

##### **返回示例**

```json
{
    "code": 0,
    "message": "success",
    "data": null
}
```



### 邮件推送

调用本接口**发送邮件通知。**

#### 基本信息

**请求方式：**POST

**请求地址：**`/api/v1/open/push/mail`

#### Body参数

| 名称        | 类型      | 是否必填 | 示例值                               | 描述                                                 |
| ----------- | --------- | -------- | ------------------------------------ | ---------------------------------------------------- |
| messageId   | String    | 是       | 2ebb14dd-1691-40c8-9078-32f97488aea3 | 通用唯一识别码，UUID。                               |
| appId       | Long      | 是       | 1L                                   | 应用唯一标识ID（不可修改），应用之间唯一。           |
| isCallBack  | Boolean   | 否       | false                                | 是否需要回调。true/false，默认为false。              |
| callBackUrl | String    | 否       | http://aaxxxaaa.com                  | 回调地址。                                           |
| requestTime | Timestamp | 是       | 1612170463260                        | 发起请求时间戳。                                     |
| sign        | String    | 是       | 2c6e52xxxxxx1e2b0bf9                 | 签名sign值。                                         |
| to          | String[]  | 是       | ["xxxxx@qq.com", "xxxxx@163.com", …] | 邮件推送收件人地址数组，邮件地址为标准可用邮箱地址。 |
| providerId  | Long      | 是       | 1L                                   | 消息平台ID，消息平台之间唯一。                       |
| subject     | String    | 否       | 这是一封邮件                         | 邮件标题。                                           |
| content     | String    | 否       | <h1 id="q3kn4">邮件</h1>             | 邮件内容，可以为富文本格式字符串。                   |
| cc          | String[]  | 否       | ["xxxxx@qq.com", "xxxxx@163.com", …] | 邮件推送抄送地址数组。                               |

#### 返回参数

| 名称    | 类型    | 示例值  | 描述                                           |
| ------- | ------- | ------- | ---------------------------------------------- |
| code    | Integer | 0       | 接口调用状态码，0表示成功，其余表示失败/异常。 |
| message | String  | success | 接口调用返回信息。                             |
| data    | Object  | null    | 调用接口返回参数，一般为null。                 |

#### 示例

##### **请求示例（HTTP）**

```http
POST http://host:port/api/v1/open/push/mail
```

##### 请求正文

```json
{
    "messageId": "2ebb14dd-1691-40c8-9078-32f97488aea3",
    "appId": 1,
    "isCallBack": false,
    "callBackUrl": null,
    "requestTime": 1612170463260,
    "sign": "2c6e52xxxxxx1e2b0bf9",
    "to": [
    	"aaaaaa@qq.com",
    	"bbbbbb@163.com"
    ],
	"providerId": 1,
	"subject": "这是一封邮件",
    "content": "<h1 id=\"q3kn4\">邮件</h1><p><i>邮件</i><br></p><p>下划线<i><br></i></p>",
	"cc": [
        "cccccc@qq.com",
        "dddddd@163.com"
    ]
}
```

##### **返回示例**

```json
{
    "code": 0,
    "message": "success",
    "data": null
}
```



### 公众号推送

调用本接口**发送公众号通知。**

#### 基本信息

**请求方式：**POST

**请求地址：**`/api/v1/open/push/wechat`

#### Body参数

| 名称        | 类型      | 是否必填 | 示例值                                                       | 描述                                       |
| ----------- | --------- | -------- | ------------------------------------------------------------ | ------------------------------------------ |
| messageId   | String    | 是       | 2ebb14dd-1691-40c8-9078-32f97488aea3                         | 通用唯一识别码，UUID。                     |
| appId       | Long      | 是       | 1L                                                           | 应用唯一标识ID（不可修改），应用之间唯一。 |
| isCallBack  | Boolean   | 否       | false                                                        | 是否需要回调。true/false，默认为false。    |
| callBackUrl | String    | 否       | http://aaxxxaaa.com                                          | 回调地址。                                 |
| requestTime | Timestamp | 是       | 1612170463260                                                | 发起请求时间戳。                           |
| sign        | String    | 是       | 2c6e52xxxxxx1e2b0bf9                                         | 签名sign值。                               |
| openId      | String    | 是       | ozZ6kxxxxxxtPP2Ck                                            | 接收者OpenId。                             |
| providerId  | Long      | 是       | 5L                                                           | 公众号推送方式id，公众号之间唯一。         |
| templateId  | Long      | 否       | 1L                                                           | 模版id，模板之间唯一。                     |
| data        | String    | 是       | {"first": {"value": "测试"},"keyword1": {"value": "预警"},"keyword2": {…}} | 模板数据。                                 |
| url         | String    | 否       | http://bbxxxbbb.com                                          | 跳转url。                                  |
| appletData  | String    | 否       | {"a": "aaaa", "b": "bbbb", …}                                | 小程序数据。                               |

#### 返回参数

| 名称    | 类型    | 示例值  | 描述                                           |
| ------- | ------- | ------- | ---------------------------------------------- |
| code    | Integer | 0       | 接口调用状态码，0表示成功，其余表示失败/异常。 |
| message | String  | success | 接口调用返回信息。                             |
| data    | Object  | null    | 调用接口返回参数，一般为null。                 |

#### 示例

##### **请求示例（HTTP）**

```http
POST http://host:port/api/v1/open/push/wechat
```

##### 请求正文

```json
{
    "messageId": "a0737527-81c6-4f2b-8c36-eae4f24972ea",
    "appId": 1,
    "isCallBack": false,
    "callBackUrl": null,
    "requestTime": 1612229507532,
    "sign": "2c6e52xxxxxx1e2b0bf9",
    "openId": "ozZ6kxxxxxxtPP2Ck",
    "providerId": 5,
    "templateId": 1,
    "data": "{\"first\": {\"value\": \"测试\"},\"keyword1\": {\"value\": \"预警\"}}",
    "url": "www.baidu.com",
    "appletData": "{\"a\": \"aaaa\", \"b\": \"bbbb\"}"
}
```

##### **返回示例**

```json
{
    "code": 0,
    "message": "success",
    "data": null
}
```



### App推送

调用本接口**发送App通知。**

#### 基本信息

**请求方式：**POST

**请求地址：**`/api/v1/open/push/app`

#### Body参数

| 名称           | 类型      | 是否必填 | 示例值                                   | 描述                                                    |
| -------------- | --------- | -------- | ---------------------------------------- | ------------------------------------------------------- |
| messageId      | String    | 是       | 2ebb14dd-1691-40c8-9078-32f97488aea3     | 通用唯一识别码，UUID。                                  |
| appId          | Long      | 是       | 1L                                       | 应用唯一标识ID（不可修改），应用之间唯一。              |
| isCallBack     | Boolean   | 否       | false                                    | 是否需要回调。true/false，默认为false。                 |
| callBackUrl    | String    | 否       | http://aaxxxaaa.com                      | 回调地址。                                              |
| requestTime    | Timestamp | 是       | 1612170463260                            | 发起请求时间戳。                                        |
| sign           | String    | 是       | 2c6e52xxxxxx1e2b0bf9                     | 签名sign值。                                            |
| providerId     | Long      | 是       | 14L                                      | 推送平台id，推送平台之间唯一。                          |
| targetPlatform | Integer   | 是       | 3                                        | 平台目标，1-ANDROID, 2-IOS, 3-全部。                    |
| registrationId | String[]  | 是       | ["160axxxxxxxe665", "180exxxxxxxa95", …] | 推送目标，用户的cid或者设备注册的registrationId。       |
| messageType    | Integer   | 是       | 2                                        | 消息类型, 1-通知（有弹窗），2-透传（App内消息，无弹窗） |
| title          | String    | 是       | 这是一个App推送                          | 消息标题。                                              |
| content        | String    | 否       | <h1>推送</h1>                            | 消息内容，可以为富文本格式字符串。                      |

#### 返回参数

| 名称    | 类型    | 示例值  | 描述                                           |
| ------- | ------- | ------- | ---------------------------------------------- |
| code    | Integer | 0       | 接口调用状态码，0表示成功，其余表示失败/异常。 |
| message | String  | success | 接口调用返回信息。                             |
| data    | Object  | null    | 调用接口返回参数，一般为null。                 |

#### 示例

##### **请求示例（HTTP）**

```http
POST http://host:port/api/v1/open/push/app
```

##### 请求正文

```json
{
    "messageId":"499d00b9-97e0-4dd1-8488-fa09ec71cb1b",
    "appId":1,
    "isCallBack": false,
    "callBackUrl": null,
    "requestTime":1612231705769,
    "sign":"2c6e52xxxxxx1e2b0bf9",
    "providerId":14,
    "targetPlatform":3,
    "registrationId":[
        "160axxxxxxxe665",
        "180exxxxxxxa935"
    ],
    "messageType":2,
    "title":"这是一个App推送",
    "content":"<h1 id=\"phakb\">推送</h1><p style=\"padding-left:2em;\"><b>test</b></p>"
}
```

##### **返回示例**

```json
{
    "code": 0,
    "message": "success",
    "data": null
}
```
