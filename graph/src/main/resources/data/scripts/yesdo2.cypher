match (a:Activity)-[r]-() delete r;
match (a:Activity) delete a;
match (m:Merchant) delete m;

create (activity_level1_num1:Activity {title:'activity_level1_num1'});
create (activity_level1_num2:Activity {title:'activity_level1_num2'});
create (activity_level1_num3:Activity {title:'activity_level1_num3'});

create (activity_level2_num1:Activity {title:'activity_level2_num1'});
create (activity_level2_num2:Activity {title:'activity_level2_num2'});

create (activity_level3_num1:Activity {title:'activity_level3_num1'});
create (activity_level3_num2:Activity {title:'activity_level3_num2'});