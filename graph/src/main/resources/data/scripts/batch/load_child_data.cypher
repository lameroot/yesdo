start n=relationship(*) delete n;
start n=node(*) delete n;

load csv with headers from "file:///Users/lameroot/Documents/yesdo/scripts/batch/activity_data.csv" as row 
with row, split(substring(row.child,1,length(row.child)-2),",") as child 
unwind child as children 
merge (a1:Activity {title: row.title})
merge (a2:Activity {title: children}) 
merge (a1)-[:ACTIVITY]->(a2); 

load csv with headers from "file:///Users/lameroot/Documents/yesdo/scripts/batch/merchant_data.csv" as row 
with row,
split(substring(row.title_activities,1,length(row.title_activities)-2),",") as title_activities 
unwind title_activities as title_act 
merge (m1:Merchant {title: row.title_merchant})
merge (a1:Activity {title: title_act})
merge (a1)-[:MERCHANT]->(m1);

load csv with headers from "file:///Users/lameroot/Documents/yesdo/scripts/batch/product_data.csv" as row 
with row,
split(substring(row.title_products,1,length(row.title_products)-2),",") as title_products
unwind title_products as title_pr 
merge (m1:Merchant {title: row.title_merchant})
merge (p1:Product {title: title_pr})
merge (m1)-[:PRODUCT]->(p1);
