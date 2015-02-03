start n=relationship(*) delete n;
start n=node(*) delete n;

load csv with headers from "file:///Users/lameroot/Documents/yesdo/scripts/batch/activity.csv" as csvLine
create (act:Activity {id: toInt(csvLine.id), title:csvLine.title});

load csv with headers from "file:///Users/lameroot/Documents/yesdo/scripts/batch/merchant.csv" as csvLine
create (mer:Merchant {id: toInt(csvLine.id), title:csvLine.title});

load csv with headers from "file:///Users/lameroot/Documents/yesdo/scripts/batch/product.csv" as csvLine
create (mer:Product {id: toInt(csvLine.id), title:csvLine.title});

load csv with headers from "file:///Users/lameroot/Documents/yesdo/scripts/batch/activity_rel.csv" as csvLine
match (root:Activity {id:toInt(cvsLine.id_root)}),child=()
create unique ()

create (mer:Product {id: toInt(csvLine.id), title:csvLine.title});