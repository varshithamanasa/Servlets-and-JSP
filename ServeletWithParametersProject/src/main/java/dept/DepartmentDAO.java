package dept;

import java.util.List;

public interface DepartmentDAO // POJO crud interface
{
	void addDepartment(Department dRef);		//	C - add - insert
	Department findDepartment(int dno);			//  R - find - select
	List<Department> findDepartments();			//  R - find - select all
	void modifyDepartment(Department dRef);		//  U - modify - update
	void removeDepartment(Department dRef);     //  D - remove - delete
}

