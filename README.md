# nongrata

This is the live coding site for the Clojure Minnesota 5/2 meeting to better 
understand Persona (BrowserID) and Clojure / Clojurescript (with Noir).

Mozilla Persona (BrowserID) aims to be a better, more usable and more privacy-conscious
implementation of OpenID. 
* [Persona Project Documentation at MDN](https://developer.mozilla.org/en-US/docs/Persona)
* [Persona Quick Setup Guide](https://developer.mozilla.org/en-US/docs/Persona/Quick_Setup)

Clojurescript, event handling and relevant client server integration with Clojure. 
* Setting up with Clojurescript with the [Clojurescript quick start](https://github.com/clojure/clojurescript/wiki/Quick-Start)
* DOM manipulation uses the excellent Clojurescript JQuery wrapper library known as [Jayq library](https://github.com/ibdknox/jayq)
* Document markup is handled (mostly) by a client side Hiccup implementation called [Crate library](https://github.com/ibdknox/crate)
* Ajax/Ahah XHR client-server integration is implemented using the Clojurescript [Fetch library](https://github.com/ibdknox/fetch)
* Using [Clojurescript with Noir](http://www.chris-granger.com/2012/02/20/overtone-and-clojurescript/)

Finally, [Noir](https://github.com/ibdknox/webnoir) is [Chris Granger's](https://github.com/ibdknox) web development library which uses Ring and Compojure.

## Running

To try it out yourself, clone this and run:

```bash
lein clean, deps, compile, cljsbuild once, run
```

And visit:

http://localhost:11300/login

Clicking on the sign-in image should do the dance with login.persona.org.
Once you sign in, refreshing the page should detect an authenticated
sign-in. It could use polish, which I might do in the next few days.

## Contributors

* tmarble
* nickbauman
* bmaddy
* others from Clojure.MN who haven't updated this file

## Jenkins

Jenkins is is running at Informatique, Inc. and rebuilding on every commit.

The *live* version of this code is pushed automatically to:

### http://nongrata.info9.net:11300/

*NOTE: the site might be down while a commit is being published*

## License

Copyright (C) 2012 Tom Marble and contributors from Clojure MN

This project is licensed under the Eclipse Public License, the same as Clojure.

