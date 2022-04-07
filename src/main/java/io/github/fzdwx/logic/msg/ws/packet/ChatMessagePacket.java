package io.github.fzdwx.logic.msg.ws.packet;

import io.github.fzdwx.inf.common.web.model.UserInfo;
import io.github.fzdwx.logic.msg.api.model.SendChatMessageReq;
import io.github.fzdwx.logic.msg.ws.WsPacket;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/4/5 20:26
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ChatMessagePacket extends WsPacket {

    private String type = Type.chat;

    /**
     * 发送者 id
     */
    private Long fromId;
    /**
     * 发送者 uname
     */
    private String fromUname;
    /**
     * 发送者 avatar
     */
    private String fromAvatar;
    /**
     * 接收者 id
     */
    private Long toId;
    /**
     * 消息体
     */
    private String content;
    /**
     * 消息类型 e.g: text, image, audio, video, file
     */
    private Integer contentType;
    /**
     * 会话类型
     *
     * @see io.github.fzdwx.logic.contants.ChatConst.SessionType
     */
    private Long sessionType;
    /**
     * @see io.github.fzdwx.logic.contants.ChatConst.MsgFrom
     */
    private Integer msgFrom;
    /**
     * 发送时间
     */
    private Date sendTime;

    public static ChatMessagePacket from(SendChatMessageReq sendChatMessageReq, UserInfo userInfo) {
        final var chatMessageVO = new ChatMessagePacket();
        chatMessageVO.setFromId(sendChatMessageReq.getFromId());
        chatMessageVO.setFromUname(userInfo.getUname());
        chatMessageVO.setFromAvatar(userInfo.getAvatar());
        chatMessageVO.setToId(sendChatMessageReq.getToId());
        chatMessageVO.setContent(sendChatMessageReq.getContent());
        chatMessageVO.setContentType(sendChatMessageReq.getContentType());
        chatMessageVO.setSessionType(sendChatMessageReq.getSessionType());
        chatMessageVO.setMsgFrom(sendChatMessageReq.getMsgFrom());
        chatMessageVO.setSendTime(sendChatMessageReq.getSendTime());

        return chatMessageVO;
    }

    @Override
    public String type() {
        return type;
    }
}