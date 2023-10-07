package com.vois.poc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vois.poc.model.UserPredictionModel;
import com.vois.poc.service.ReadCsvDataService;
import org.springframework.http.ResponseEntity;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/userTask")
@CrossOrigin
public class CamundaController {

    private final TaskService taskservice;

    public CamundaController(TaskService taskservice) {

        this.taskservice = taskservice;
    }

    @PostMapping("/claimTask")
    public ResponseEntity<String> claimTask(@RequestBody Map<String, Object> claimTask) {
        String response = null;
        ObjectMapper mapper = new ObjectMapper();
        //Claim User task
        if (null != claimTask.get("taskId")) {
            Object task = claimTask.get("taskId");
            taskservice.claim(task.toString(), "agent");
            response = "Claimed";
            //Complete User task
            if (response.equals("Claimed")) {
//                UserPredictionModel predictedData = mapper.convertValue(claimTask.get("predictedData"),UserPredictionModel.class);
                taskservice.complete(claimTask.get("taskId").toString(), claimTask);
                response = "User task " + claimTask.get("taskId") + "is Completed Successfully";
            } else {
                response = "Invalid/Null taskId for " + claimTask.get("taskId");
            }
        } else {
            response = "Invalid/Null taskId for " + claimTask.get("processId");
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/completeTask")
    public ResponseEntity<String> completeTask(@RequestBody Map<String, Object> completeTask) {
        String response = null;
        if (null != completeTask.get("taskId")) {

            HashMap<String, Object> predictedData = (HashMap<String, Object>) completeTask.get("predictedData");
            taskservice.complete(completeTask.get("taskId").toString(), predictedData);
            response = "User task " + completeTask.get("taskId") + "is Claimed";
        } else {
            response = "Invalid/Null taskId for " + completeTask.get("processId");
        }
        return ResponseEntity.ok(response);
    }
}
