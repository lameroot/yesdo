match (a:Activity)-[r]-() delete r;
match (a:Activity) delete a;
match (m:Merchant) delete m;

create (activity_root:Activity {title:'activity_root'})
create (activity_level1_num1:Activity {title:'activity_level1_num1'})
create (activity_level1_num2:Activity {title:'activity_level1_num2'})
create (activity_level1_num3:Activity {title:'activity_level1_num3'})

create (activity_level2_num1:Activity {title:'activity_level2_num1'})
create (activity_level2_num2:Activity {title:'activity_level2_num2'})

create (activity_level3_num1:Activity {title:'activity_level3_num1'})
create (activity_level3_num2:Activity {title:'activity_level3_num2'})

create unique (activity_root)-[:ACTIVITY]->(activity_level1_num1)
create unique (activity_root)-[:ACTIVITY]->(activity_level1_num2)
create unique (activity_root)-[:ACTIVITY]->(activity_level1_num3)

create unique (activity_level1_num1)<-[:ACTIVITY]->(activity_level2_num1)
create unique (activity_level1_num2)<-[:ACTIVITY]->(activity_level2_num1)
create unique (activity_level1_num2)<-[:ACTIVITY]->(activity_level2_num2)
create unique (activity_level1_num3)<-[:ACTIVITY]->(activity_level2_num2)
create unique (activity_level2_num1)<-[:ACTIVITY]->(activity_level3_num1)
create unique (activity_level2_num2)<-[:ACTIVITY]->(activity_level3_num2)

create (merchant_level2_num1:Merchant {title:'merchant_level2_num1'})
create (merchant_level2_num2:Merchant {title:'merchant_level2_num2'})
create (merchant_level3_num1:Merchant {title:'merchant_level3_num1'})

create unique (merchant_level2_num1)<-[:MERCHANT]->(activity_level2_num1)
create unique (merchant_level2_num2)<-[:MERCHANT]->(activity_level2_num1)
create unique (merchant_level2_num2)<-[:MERCHANT]->(activity_level2_num2)
create unique (merchant_level3_num1)<-[:MERCHANT]->(activity_level3_num1)
create unique (merchant_level3_num1)<-[:MERCHANT]->(activity_level3_num2)

create (product_merchant_level2_num1_num1:Product {title:'product_merchant_level2_num1_num1'})
create (product_merchant_level2_num1_num2:Product {title:'product_merchant_level2_num1_num2'})

create (product_merchant_level2_num2_num1:Product {title:'product_merchant_level2_num2_num1'})
create (product_merchant_level2_num2_num2:Product {title:'product_merchant_level2_num2_num2'})

create (product_merchant_level3_num1_num1:Product {title:'product_merchant_level3_num1_num1'})
create (product_merchant_level3_num1_num2:Product {title:'product_merchant_level3_num1_num2'})
create (product_merchant_level3_num1_num3:Product {title:'product_merchant_level3_num1_num3'})

create unique (product_merchant_level2_num1_num1)<-[:PRODUCT {amount:100}]->(merchant_level2_num1)
create unique (product_merchant_level2_num1_num2)<-[:PRODUCT {amount:110}]->(merchant_level2_num1)

create unique (product_merchant_level2_num2_num1)<-[:PRODUCT {amount:200}]->(merchant_level2_num2)
create unique (product_merchant_level2_num2_num2)<-[:PRODUCT {amount:210}]->(merchant_level2_num2)

create unique (product_merchant_level3_num1_num1)<-[:PRODUCT {amount:300}]->(merchant_level3_num1)
create unique (product_merchant_level3_num1_num2)<-[:PRODUCT {amount:310}]->(merchant_level3_num1)
create unique (product_merchant_level3_num1_num3)<-[:PRODUCT {amount:320}]->(merchant_level3_num1)
;
