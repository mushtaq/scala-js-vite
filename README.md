This is an example project showing how to work with Scala.js with vite build. 
JS dependencies are managed in package.json
HMR, tests and prod bundling work as expected (and fast!)

### Before you start
```
$ cd example
$ npm install
```

### Dev flow

Start dev server in terminal-1
```
$ cd example
$ npm run dev
```

Setup incremental Scala.js linking in terminal-2
```
$ sbt
sbt:scala-js-vite> project example
sbt:example> ~fastLinkJS
```

Open http://localhost:3000/ and watch the console. 

Make changes in Main.scala and watch HMR(Hot Module Reloading) working in the console.

### Test flow

Ensure dev server is running in terminal-1
```
$ cd example
$ npm run dev
```

Run Scala.js tests in terminal-2
```
$ sbt
sbt:scala-js-vite> project example
sbt:example> test
```

### Prod flow

In index.html, comment the script tag pointing to `example-fastopt/main.js` 
and uncomment the following script tag pointing to `example-opt/main.js`

Run the full linking in Scala.js
```
$ sbt
sbt:scala-js-vite> project example
sbt:example> fullLinkJS
```

Run npm build
```
$ cd example
$ npm run build
```

Verify the build with a static web server
```
$ cd example/dist
$ python3 -m http.server 4000
```

Open http://localhost:3000/ and see the console.
