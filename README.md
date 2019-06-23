# Validator for Java

[![Build Status](https://travis-ci.org/bitschen/validator.svg?branch=master)](https://travis-ci.org/bitschen/validator)

ValidatorJava是一个参数校验框架，应用于非Spring体系的Web开发中，目前我用于[Sparkjava](https://github.com/perwendel/spark)Web框架下的程序开发。SparkJava是一个轻量级JavaWeb框架，它与Spring不同的是占用资源很少。在物联网线下场景，Spring就是个性能怪兽，Spark则是非常合适的选择。但Spark缺少相应的参数校验库，[Validator](https://github.com/bitschen/validator)是为Spark场景设计，已应用于多个项目。

## 配置

```gradle
repositories {
	maven {
		url  "https://dl.bintray.com/nextabc/maven"
	}
}
// And add
compile 'net.nextabc:validator:1.1.3'
```

## 使用

## 校验模式

## Author

- Bits Chen
