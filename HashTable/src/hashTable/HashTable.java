package hashTable;

/**
 * @author dll
 * @date 20220406
 */
public class HashTable {
    private EmpLinkList[] empLinkListArray;
    private int size;

    public HashTable(int size) {
        empLinkListArray = new EmpLinkList[size];
        //分别初始化每条链表
        for (int i = 0; i < size; i++) {
            empLinkListArray[i] = new EmpLinkList();
        }
        this.size = size;
    }

    public void add(Employee employee) {
        //根据员工的id得到该员工应该添加到那条链表
        int empLinkListNo = hashFun(employee.getId());
        //将emp添加到对应的链表中
        empLinkListArray[empLinkListNo].add(employee);
    }

    public void list() {
        //遍历整个hash表
        for (int i = 0; i < size; i++) {
            empLinkListArray[i].list(i);
        }
    }

   public void findEmpById(int id) {
        int empLinkListNo = hashFun(id);
        Employee employee = empLinkListArray[empLinkListNo].findById(id);
        if (employee != null) {
            System.out.print("在第"+(empLinkListNo+1)+"找到雇员");
            System.out.println(" ==该雇员的姓名: "+employee.getName()+"id: "+employee.getId());
        } else {
            System.out.println("在哈希表中没有找到雇员");
        }
   }

   public void deleteById(int id) {
        empLinkListArray[hashFun(id)].delete(id);
   }

    public int hashFun(int id) {
        return id % size;
    }


}

