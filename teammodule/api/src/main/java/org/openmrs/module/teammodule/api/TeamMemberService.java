/**
 * 
 */
package org.openmrs.module.teammodule.api;

import java.util.Date;
import java.util.List;

import org.openmrs.api.OpenmrsService;
import org.openmrs.module.teammodule.Team;
import org.openmrs.module.teammodule.TeamMember;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Muhammad Safwan
 *
 */
@Transactional 
public interface TeamMemberService extends OpenmrsService {
	
	public void save(TeamMember teamMember);
	
	public List<TeamMember> getTeamMembers(Team team, String name, Integer teamLeadId, Boolean retired);
	
	public List<TeamMember> getTeamMembers(Integer id);
	
	public List<TeamMember> getAllMembers(boolean retired);
	
	public List<TeamMember> getMembers(Date joinDateFrom, Date joinDateTo);
	
	public TeamMember getMember(int id);
	
	public List<TeamMember> getMember(String name);
	
	public List<TeamMember> searchMember(String name);
	
	List<TeamMember> searchMemberByTeam(String name,int teamId);
	
	//public Query searchMember(String name);
	
	public void purgeMember(TeamMember teamMember);
	
	public void update(TeamMember teamMember);
	
	//public SQLQuery getCount(Integer teamId);
	
	//public List<TeamMember> getLikeMember(String name);

}
