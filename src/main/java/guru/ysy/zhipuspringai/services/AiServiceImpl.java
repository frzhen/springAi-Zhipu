package guru.ysy.zhipuspringai.services;

import guru.ysy.zhipuspringai.models.Answer;
import guru.ysy.zhipuspringai.models.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.image.ImageOptions;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.zhipuai.ZhiPuAiImageModel;
import org.springframework.ai.zhipuai.ZhiPuAiImageOptions;
import org.springframework.stereotype.Service;

/**
 * @Author: Fred R. Zhen
 * @Date: 2024/6/2 16:15
 * @Email: fred.zhen@gmail.com
 */
@RequiredArgsConstructor
@Service
public class AiServiceImpl implements AiService {

    final ZhiPuAiImageModel zhiPuAiImageModel;
    @Override
    public Answer getImage(Question question) {

        ImageOptions options = ZhiPuAiImageOptions.builder().build();

        ImagePrompt imagePrompt = new ImagePrompt(question.question(), options);
        ImageResponse imageResponse = zhiPuAiImageModel.call(imagePrompt);
        return new Answer(imageResponse.getResult().getOutput().getUrl());
    }
}
