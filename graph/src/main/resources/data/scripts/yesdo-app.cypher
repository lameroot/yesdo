match (a:Activity)-[r]-() delete r;
match (a:Activity) delete a;
match (m:Merchant) delete m;

create (activity:Activity {title:'activity_level1_num1'});
create (activity:Activity {title:'activity_level1_num2'});
create (activity:Activity {title:'activity_level1_num3'});

create (activity:Activity {title:'activity_level2_num1'});
create (activity:Activity {title:'activity_level2_num2'});

create (activity:Activity {title:'activity_level3_num1'});
create (activity:Activity {title:'activity_level3_num2'});

match (a1:Activity) where a1.title='activity_level1_num1' 
match (a2:Activity) where a2.title='activity_level2_num1' 
create unique (a1)<-[:ACTIVITY]->(a2);

match (a1:Activity) where a1.title='activity_level1_num2' 
match (a2:Activity) where a2.title='activity_level2_num1' 
create unique (a1)<-[:ACTIVITY]->(a2);

match (a1:Activity) where a1.title='activity_level1_num2' 
match (a2:Activity) where a2.title='activity_level2_num2' 
create unique (a1)<-[:ACTIVITY]->(a2);

match (a1:Activity) where a1.title='activity_level1_num3' 
match (a2:Activity) where a2.title='activity_level2_num2' 
create unique (a1)<-[:ACTIVITY]->(a2);

match (a1:Activity) where a1.title='activity_level2_num1' 
match (a2:Activity) where a2.title='activity_level3_num1' 
create unique (a1)<-[:ACTIVITY]->(a2);

match (a1:Activity) where a1.title='activity_level2_num2' 
match (a2:Activity) where a2.title='activity_level3_num2' 
create unique (a1)<-[:ACTIVITY]->(a2);

create (merchant_level2_num1:Merchant {title:'merchant_level2_num1'});
create (merchant_level2_num2:Merchant {title:'merchant_level2_num2'});
create (merchant_level3_num1:Merchant {title:'merchant_level3_num1'});

match (a2:Activity) where a2.title='activity_level2_num1' 
match (m:Merchant) where m.title='merchant_level2_num1' 
create unique (m)<-[:MERCHANT]->(a2);

match (a2:Activity) where a2.title='activity_level2_num1' 
match (m:Merchant) where m.title='merchant_level2_num2' 
create unique (m)<-[:MERCHANT]->(a2);

match (a2:Activity) where a2.title='activity_level2_num2' 
match (m:Merchant) where m.title='merchant_level2_num2' 
create unique (m)<-[:MERCHANT]->(a2);

match (a2:Activity) where a2.title='activity_level3_num1' 
match (m:Merchant) where m.title='merchant_level3_num1' 
create unique (m)<-[:MERCHANT]->(a2);

