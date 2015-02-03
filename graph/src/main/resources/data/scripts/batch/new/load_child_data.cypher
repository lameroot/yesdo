start n=relationship(*) delete n;
start n=node(*) delete n;

load csv with headers from "file:///D:/Users/krainov/Documents/soft/java/workspaces_idea/yesdo/yesdo/graph/src/main/resources/data/scripts/batch/new/activity_data.csv" as row
with row, split(substring(row.child,1,length(row.child)-2),",") as child 
unwind child as children 
merge (a1:Activity {title: row.title})
merge (a2:Activity {title: children}) 
merge (a1)-[:ACTIVITY]->(a2);

load csv with headers from "file:///D:/Users/krainov/Documents/soft/java/workspaces_idea/yesdo/yesdo/graph/src/main/resources/data/scripts/batch/new/merchant_data.csv" as row
with row,
split(substring(row.title_activities,1,length(row.title_activities)-2),",") as title_activities
unwind title_activities as title_act
merge (m1:Merchant {title: row.title_merchant})
merge (a1:Activity {title: title_act})
merge (a1)-[:MERCHANT]->(m1);

load csv with headers from "file:///D:/Users/krainov/Documents/soft/java/workspaces_idea/yesdo/yesdo/graph/src/main/resources/data/scripts/batch/new/product_data.csv" as row
with row
merge (m1:Merchant {title: row.title_merchant})
merge (p1:Product {title: row.title_product})
merge (m1)-[:PRODUCT {amount: toInt(row.amount_product)}]->(p1)
;