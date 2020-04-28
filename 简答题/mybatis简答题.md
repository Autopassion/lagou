# 简答题

##  1、Mybatis动态sql是做什么的？都有哪些动态sql？简述一下动态sql的执行原理？

Mybatis动态sql是用来帮助开发人员根据不同的条件来进行sql语句的拼接，避免手动拼接繁琐的过程。

在Mybatis3.0版本之前，动态sql主要依赖于各式各样的标签来实现，现在的3.x版本的Mybatis借助于OGNL表达式语言消除了很多的标签，现在的动态sql主要依托于：if、choose（when,otherwise）、trim(where,set)、foreach，bind来实现。

动态sql与普通sql语句对比，动态sql的内部包含了很多动态DynamicTag，所以动态sql执行原来的核心在于mybatis如何解析这些DynamicTag，mybatis提供parseDynamicTags方法解析statement的XML文件内容，如果是文本或者CDATA，就构造一个TextSqlNode或者StaticTextSqlNode，如果是元素，需要根据元素标签的类型调用相关的TagHandler进行处理，最终返回一个List<SqlNode>，再对该集合的内容进行处理，返回BoundSql。



##  2、Mybatis是否支持延迟加载？如果支持，它的实现原理是什么？

MyBatis中对于延迟加载设置，只对于resultMap中的collection和association起作用，可以应用到一对一、一对多、多对一、多对多的所有关联关系查询中。

它的原理是，使用CGLIB创建目标对象的代理对象，当调用目标方法时，进入拦截器方法，比如调用a.getB().getName()，拦截器invoke()方法发现a.getB()是null值，那么就会单独发送事先保存好的查询关联B对象的sql，把B查询上来，然后调用a.setB(b)，于是a的对象b属性就有值了，接着完成a.getB().getName()方法的调用 



## 3、Mybatis都有哪些Executor执行器？它们之间的区别是什么？

SimpleExecutor：每执行一次update或select，就开启一个Statement对象，用完立刻关闭Statement对象。

ReuseExecutor：执行update或select，以sql作为key查找Statement对象，存在就使用，不存在就创建，用完后，不关闭Statement对象，而是放置于Map内，供下一次使用。简言之，就是重复使用Statement对象。

BatchExecutor：执行update（没有select，JDBC批处理不支持select），将所有sql都添加到批处理中（addBatch()），等待统一执行（executeBatch()），它缓存了多个Statement对象，每个Statement对象都是addBatch()完毕后，等待逐一执行executeBatch()批处理。与JDBC批处理相同。



## 4、简述下Mybatis的一级、二级缓存（分别从存储结构、范围、失效场景。三个方面来作答）？

一级缓存通过HashMap来存储查询结果，key由mapper文件的namespace、statementId、offset、limit、sql语句、参数、environment的id构成，作用范围是sqlsession生命周期内，失效场景主要包括sqlseesion生命周期内进行了update或者delete操作，或者跨session的sql操作；

二级缓存默认使用Cache接口的实现类PerpetualCache来存储数据，每个namespace对应一个缓存对象，实现了SqlSession之间的数据共享，失效的场景主要包括：进行了delete或者update操作，并配置了flushCache选项。



##  5、简述Mybatis的插件运行原理，以及如何编写插件？

Mybatis仅可以编写针对ParameterHandler、ResultSetHandler、StatementHandler、Executor这4种接口的插件，Mybatis使用JDK的动态代理， 为需要拦截的接口生成代理对象以实现接口方法拦截功能，每当执行这4种接口对象的方法时，就会进入拦截方法，具体就是InvocationHandler的invoke()方法，当然，只会拦截那些你指定需要拦截的方法。 

编写插件：实现Mybatis的Interceptor接口并复写intercept()方法，然后在给插件编写注解，指定要拦截哪一个接口的哪些方法即可，记住，别忘了在配置文件中配置你编写的插件。 








