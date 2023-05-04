package extend;

import java.util.*;

public class TestClass {
    public static void main(String[] args) {
        List<SyncBaseClass> list = new ArrayList();
        SyncSchool syncSchool = new SyncSchool();
        syncSchool.setTime(new Date());
        syncSchool.setSchoolName("学校1");
        list.add(syncSchool);

        SyncSchool syncSchool2 = new SyncSchool();
        syncSchool2.setTime(new Date(1621416724000l));
        syncSchool2.setSchoolName("学校2");
        list.add(syncSchool2);

        SyncStudent syncStudent = new SyncStudent();
        syncStudent.setTime(new Date(1621416769992l));
        syncStudent.setStudentName("学生一");
        list.add(syncStudent);

//        List<SyncBaseClass> newList = list.stream().sorted(Comparator.comparing(SyncBaseClass::getSort)).collect(Collectors.toList());

        Collections.sort(list, new Comparator<SyncBaseClass>() {
            @Override
            public int compare(SyncBaseClass o1, SyncBaseClass o2) {
                // TODO Auto-generated method stub
                return o1.getTime().compareTo(o2.getTime());
            }
        });
        System.out.println("ddddddddddd");

    }
}