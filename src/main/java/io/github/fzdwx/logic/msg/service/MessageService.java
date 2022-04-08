package io.github.fzdwx.logic.msg.service;

import cn.hutool.core.util.ObjectUtil;
import io.github.fzdwx.inf.common.exc.Exceptions;
import io.github.fzdwx.inf.common.web.Web;
import io.github.fzdwx.logic.domain.dao.ChatLogDao;
import io.github.fzdwx.logic.msg.api.model.SendChatMessageReq;
import io.github.fzdwx.logic.msg.ws.listener.event.SendMessageEvent;
import io.github.fzdwx.logic.msg.ws.packet.ChatMessagePacket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:likelovec@gmail.com">fzdwx</a>
 * @date 2022/4/5 19:38
 */
@Service
@RequiredArgsConstructor
public class MessageService {

    private final ChatLogDao chatLogDao;

    public void send(final SendChatMessageReq sendChatMessageReq) {
        final var userInfo = Web.getUserInfo();
        if (ObjectUtil.isNull(chatLogDao.save(sendChatMessageReq))) {
            throw Exceptions.normal("保存聊天记录失败");
        }

        final var packet = ChatMessagePacket.from(sendChatMessageReq, userInfo);

        SendMessageEvent.routing(userInfo.getId(), packet);
    }
}