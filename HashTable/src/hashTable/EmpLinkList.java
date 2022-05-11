package hashTable;

/**
 * @author dll
 */
public class EmpLinkList {
    //头指针，指向第一个emp，没有专门的头结点，直接指向emp
    private Employee head;

    /**
     * 假定雇员直接添加到链表最后
     *
     * @param employee 要添加的雇员
     */
    public void add(Employee employee) {
        if (head == null) {
            head = employee;
            return;
        }
        //如果不是第一个雇员，则用一个辅助指针，定位到最后
        Employee currentEmp = head;
        while (true) {
            if (currentEmp.getNext() == null) {
                break;
            }
            currentEmp = currentEmp.getNext();
        }
        currentEmp.setNext(employee);
    }

    public void list(int no) {
        if (head == null) {
            System.out.println("第 " + (no + 1) + " 条链表为空");
            return;
        }
        System.out.print("第 " + (no + 1) + " 条链表信息为:");
        Employee currentEmp = head;
        while (true) {
            System.out.printf(" => id = %d name = %s\t", currentEmp.getId(), currentEmp.getName());
            if (currentEmp.getNext() == null) {
                break;
            }
            currentEmp = currentEmp.getNext();
        }
        System.out.println("");
    }

    public Employee findById(int id) {
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        Employee currentEmp = head;
        while (true) {
            if (currentEmp.getId() == id) {
                //找到，此时curEmp指向要找的雇员
                break;
            }
            if (currentEmp.getNext() == null) {
                //遍历当前链表没有找到
                currentEmp = null;
                //在while语句中，将指针置为空，需要及时break
                break;
            }
            currentEmp = currentEmp.getNext();
        }
        return currentEmp;
    }

    public void delete(int id) {
        if (head == null) {
            System.out.println("链表为空，无需删除");
            return;
        }
        Employee currentEmp = head;
        boolean flag = false;
        while (true) {
            if (currentEmp.getNext() == null) {
                if (currentEmp.getId() == id) {
                    //有且仅有一个节点，就是要找到id
                    head = null;
                    flag = true;
                    break;
                } else {
                    currentEmp = null;
                    break;
                }
            }
            if (currentEmp.getId() == id) {
                //长链表且第一个就是
                flag = true;
                head = currentEmp.getNext();
                break;
            }
           if (currentEmp.getNext().getId() == id) {
               //要找的id在第二个以后
               flag = true;
               currentEmp.setNext(currentEmp.getNext().getNext());
               break;
           }
            currentEmp = currentEmp.getNext();
        }
        if (flag) {
            System.out.println(id + "号节点已删除");
        } else {
            System.out.println("要找的节点不存在");
        }
    }

    public Employee getHead() {
        return head;
    }
}
