package persistence.dao;

import java.util.List;
import service.dto.MsgDTO;

public interface MsgDAO {

	public List<MsgDTO> ReceiveMsgList(String userId);
	public List<MsgDTO> SendMsgList(String userId);
	public int sendMsg(MsgDTO msg);
	public int deleteMsg(String msgNo);
	public int searchMsg(String data);
}
