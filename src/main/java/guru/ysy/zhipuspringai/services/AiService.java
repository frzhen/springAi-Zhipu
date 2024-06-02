package guru.ysy.zhipuspringai.services;

import guru.ysy.zhipuspringai.models.Answer;
import guru.ysy.zhipuspringai.models.Question;

/**
 * @Author: Fred R. Zhen
 * @Date: 2024/6/2 16:14
 * @Email: fred.zhen@gmail.com
 */
public interface AiService {
    Answer getImage(Question question);
}
