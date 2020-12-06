package com.itheima.day05;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

/**
 *  测试包含网关
 *     特点：具有排他网关和并行网并的一些共同点
 *           可以设置流程变量，当流程变量取值都成立时，此时若干个分支都可以执行
 *
 */
public class InclusiveGateWayTest {

    //3.任务执行完成
    public static void main(String[] args) {
        //1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        //2.得到TaskService对象
        TaskService taskService = processEngine.getTaskService();

        //3.查询当前用户的任务
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("examine")
                .taskAssignee("xiaowang")
                .singleResult();

        //4.处理任务,结合当前用户任务列表的查询操作的话,任务ID:task.getId()
        if(task!=null){
            taskService.complete(task.getId());
            System.out.println("用户任务执行完毕...");
        }


        //5.输出任务的id
        System.out.println(task.getId());
    }

    //2.启动流程实例
/*   public static void main(String[] args) {
            //1.得到ProcessEngine对象
            ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

            //2.得到RunService对象
            RuntimeService runtimeService = processEngine.getRuntimeService();

        Integer userType = 2;//代表管理者
        Map<String,Object> map = new HashMap<>();
        map.put("userType",userType);//流程变量赋值

            //3.创建流程实例  流程定义的key需要知道 holiday
            ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("examine",map);


            //4.输出实例的相关信息
            System.out.println("流程定义ID"+processInstance.getProcessDefinitionId());//holiday:1:4
            System.out.println("流程实例ID"+processInstance.getId());//2501
    }*/


    //1.部署流程定义  带排他网关，同时还带并行网关
/*    public static void main(String[] args) {
        //1.创建ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        //2.得到RepositoryService实例
        RepositoryService repositoryService = processEngine.getRepositoryService();

        //3.进行部署
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("diagram/day05/examine.bpmn")  //添加bpmn资源
                //.addClasspathResource("diagram/day05/examine.png")
                .name("体检流程")
                .deploy();

        //4.输出部署的一些信息
        System.out.println(deployment.getName());
        System.out.println(deployment.getId());
    }*/
}
