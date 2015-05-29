
## PD Litmus: a 'semantic' coloring module

This is a little text filter that puts color-styled spans around nonterminal words within &lt;code&gt; tags. Eventually intended as a plugin for PDistillery. Also useful as a standalone library to color your self-hosted code in html.

This currently has _prototypical_ behavior. The color is set by sine waves along RGB, the spans go around every three characters, and so on. _Eventually_, I intend to walk the LAB colorspace to color the variables in a couple languages I use.

MIT License 2015, as described at [choosealicense.com/licenses/mit/]

### Todo (14 May)

* LitmusFrame: build transmix from a file
* Litmus: break the input into non/code blocks
* Transmix: take a Path object instead of a filepath
* Colorspace: bislice the rgb colorspace
* LangLexer: use a hard regex that avoids keywords/operators
* SyntaxPDautoma: diagnose why it's not marking anything as terminal
* (all): tests for the various pieces