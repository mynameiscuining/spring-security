# spring-security
学习笔记:spring-security  

注解含义
====
@PageDefault 指定分页参数的默认值   
@JsonView  
根据场景不同的场景同一对象，设置返回属性是否有值   
  1.使用方法(示例User对象)    
     使用接口声明多个视图  
     在值对象get方法上指定视图  
     在controller类的方法上指定视图  
  2.注意事项  
    (1)如果对象创建了视图并且在controller使用了视图，满足这两个条件，对象  
    属性没有注解对应视图和没有视图的属性,结果字段属性都不会显示  
    

