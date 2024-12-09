package ch.m295;

import java.util.List;

public interface IArbeitgeberDAO {

	public List<Arbeitgeber> select();
	public Arbeitgeber select(Arbeitgeber a);
	public int insert(Arbeitgeber a);
	public int update(Arbeitgeber a, Arbeitgeber b);
	public int delete(String name);
	
}
