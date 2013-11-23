BPTexEd Readme
==============

BPTexEd is a text editor written in BlitzPlus for Windows. That's all there is
to it.

...

Okay, here's some more information.


Requirements
------------

Since BlitzPlus is a Windows-only program, I would suggest a good copy of
Windows.

So how do you compile the darn thing? Simple! Open it with the lastest version
of the BlitzPlus IDE, then on the menu bar go to Program and click
whatever option your little heart desires.

On command-line the following can be accomplished (assuming blitzcc is in your
path) with:

```
blitzcc bptexed.bb
```

Coding Style
------------

I really like consistency (even though the tone of this README is not
consistent; bear with me) should I'd appreciate if all code looked like all
other code. There are also other benefits but consistency is a huge thing for
me.

Comments should conform as closely as possible to doxygen. It is forgivable if
they are a bit sloppy (even I don't know everything about doxygen!).

If you define a type `TypeA`, then all functions relating to it should be
prefixed with `TypeA_`. This helps clarify what the function is operating on,
and the underscore prevents name clashing with the defeault BlitzPlus
functions.

Lines should try to stay below 80 characters. For indentation, just look at how
it's done with existing code.

Function calls should look like this if they don't return a value:

```
FunctionName foo, bar, baz
```

...Or like this if they DO return a value:

```
FuncWithReturn( foo, bar, baz )
```

Final Notes
-----------

If you are using the default BlitzPlus IDE it tends to generate (by default)
`*.bb_back[0-9]+` backups as you save. This should be added to .gitignore.