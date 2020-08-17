package com.yzl.yujudge.dto;

/**
 * @author yuzhanglong
 * @date 2020-8-17 22:29:42
 * @description 判题服务器连接测试数据传输对象
 */
public class JudgeHostConnectionDTO extends JudgeHostResponseDTO {
    private JudgeHostConditionDTO data;

    public JudgeHostConditionDTO getData() {
        return data;
    }

    public void setData(JudgeHostConditionDTO data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "JudgeHostConnectionDTO{" +
                "data=" + data +
                '}';
    }
}
