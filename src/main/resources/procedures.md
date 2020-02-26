# Publish to CF

## Action TODOs & FOCUS

## Git

Commit
Create vXY if required

## Update application.properties 

jdbc.url=jdbc:mysql://us-cdbr-iron-east-04.cleardb.net:3306/ad_50bca53daa359fe?useSSL=false
jdbc.user=b8c33d8ada9f50
jdbc.password=b19c97ed

## Test that it works locally

Maven > Update Project
Run As > Run on Server

## Build war

Stop the server
Delete the old war file
Run As > Maven install

## Push to CF

"d:/program files/cloud foundry/cf" push monivapp -p target/monivapp.war

## Log in if required

"d:/program files/cloud foundry/cf" login -a https://api.run.pivotal.io

## Change back to 

jdbc.url=jdbc:mysql://localhost:3306/monivapp?useSSL=false
jdbc.user=monivapp
jdbc.password=koliko

## Other

monivapp-client/dist/monivapp-client>"d:/program files/cloud foundry/cf" push monivapp-client
Check for monivapp-client/dist/monivapp-client>Staticfile
Check for monivapp-client/dist/monivapp-client>manifest.yml