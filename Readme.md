
## PD Litmus: a 'semantic' coloring module

This is a little text filter that puts color-styled spans around nonterminal words within &lt;code&gt; tags. Eventually intended as a plugin for PDistillery. Also useful as a standalone library to color your self-hosted code in html.

This currently has _prototypical_ behavior. The color is set by sine waves along RGB, the spans only ignore whitespace and math chars, and so on. _Eventually_, I intend to walk the LAB colorspace to color the variables in a couple languages I use.

MIT License 2015, as described at [choosealicense.com](http://choosealicense.com/licenses/mit/)

### Todo (29 May)

* LitmusFrame: build a legit transmix, alter the path to determine where output goes
* Litmus: process code blocks concurrently?, audit api for plugin use
* Transmix: require a Path object instead of a filepath
* Colorspace: bislice the rgb colorspace
* LangLexer: take a config collection of operators and keywords for arbitrary langs
* SyntaxPDautoma: ignore multichar operators, ignore numbers
* (all): tests for the various pieces