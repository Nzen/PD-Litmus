
## PD Litmus: a 'semantic' coloring module

This is a little text filter that puts colored spans around nonterminal words within <code> tags. Eventually intended as a plugin for PDistillery. Also useful as a standalone library to color your self-hosted code in html.

This currently has prototypical behavior. The color is set by sine waves along RGB, the spans go around every three characters, and so on. Eventually, I intend to walk the LAB colorspace to color the variables in a couple languages I use.

MIT License 2015, as described at http://choosealicense.com/licenses/mit/

### Todo (14 May)

* LitmusFrame: build transmix from a file
* Litmus: break the input into non/code blocks
* Transmix: take a Path object instead of a filepath
* Colorspace: bislice the rgb colorspace
* LangLexer: use a hard regex that avoids keywords/operators
* (all): tests for the various pieces