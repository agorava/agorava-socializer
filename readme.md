#Welcome to Agorava Socializer
Socializer is a demonstration Web App for Agorava. It allows you to connect to various social Media, and see your timeline and post update.

##Running
Socializer comes with a Jetty configuration, so you can lauch the webapp directly from maven. All you have to do is enter the command

`mvn -Prun`

and wait that maven download all its needed stuff (can be long the first time)
When Jetty is launch just point your browser to
`http://localhost:8080`
and enjoy.


##Building
You can build Agorava with the command

`mvn clean install`

It'll produce the war `agorava-socializer.war` in target Folder. 

##Deploy
Right now Socializer was tested with JBoss 7.1.1 and WildFly 8.x. We are in the process of testing validating it with others servers

##Issues
Please report your issues to
`https://issues.jboss.org/browse/AGOVA`