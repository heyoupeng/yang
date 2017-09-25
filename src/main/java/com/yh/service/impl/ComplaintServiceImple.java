package com.yh.service.impl;

import java.util.List;

import com.yh.dao.ComplaintDao;
import com.yh.dao.impl.ComplaintDaoImple;
import com.yh.model.Complaint;
import com.yh.service.ComplaintService;

public class ComplaintServiceImple implements ComplaintService{
	ComplaintDao cdao=new ComplaintDaoImple();

	@Override
	public List<Complaint> searchComplaintByObj(Complaint c,int page,int pageSize) {
		if(c.getMno()==0)
		{
			List<Complaint> complaints=cdao.selectComplaintByNothing(c,page,pageSize);
			return complaints;
		}else
		{
			List<Complaint> complaints=cdao.selectComplaintByObj(c,page,pageSize);
			return complaints;
		}
		
	}

	@Override
	public boolean insertComplaintByObj(Complaint c) {
		boolean flag=cdao.insertComplaintByObj(c);
		return flag;
	}

	@Override
	public boolean changeComplaintByObj(Complaint c) {
		boolean flag=cdao.updateComplaintByObj(c);
		return flag;
	}

	@Override
	public boolean revokeComplaintByCid(int cid) {
		boolean flag=cdao.deleteComplaintByCid(cid);
		return flag;
	}

	@Override
	public List<Complaint> searchComplaintByMySelf(Complaint c,int page,int pageSize) {
		List<Complaint> complaints=cdao.selectComplaintByMySelf(c,page,pageSize);
		return complaints;
	}

	@Override
	public List<Complaint> searchComplaintByMySelfAll(Complaint c,int page,int pageSize) {
		List<Complaint> complaints=cdao.selectComplaintByMySelfAll(c,page,pageSize);
		return complaints;
	}

	@Override
	public boolean acceptComplaintsByCid(int cid) {
		boolean flag=cdao.acceptComplaintsByCid(cid);
		return flag;
	}

	@Override
	public boolean submitResultByObj(Complaint c) {
		boolean flag=cdao.updateComplaintsByObj(c);
		return flag;
	}

}
