package guru.ysy.zhipuspringai.controllers;

import guru.ysy.zhipuspringai.models.Answer;
import guru.ysy.zhipuspringai.models.Question;
import guru.ysy.zhipuspringai.services.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Fred R. Zhen
 * @Date: 2024/6/2 16:10
 * @Email: fred.zhen@gmail.com
 */
@RequiredArgsConstructor
@RestController
public class QuestionController {

    private final AiService aiService;

    @PostMapping(value = "/image")
    public ResponseEntity<Answer> getImage(@RequestBody Question question) {
        Answer answer = aiService.getImage(question);
        return new ResponseEntity<>(answer, null, 200);
    }
}
