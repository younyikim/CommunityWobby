package persistence.dao;

import java.util.List;

import service.dto.AlarmDTO;

public interface AlarmDAO {
	
	public List<AlarmDTO> getAlarmList(String userId);
	public int deleteAlarm(int alarmNo);
}
