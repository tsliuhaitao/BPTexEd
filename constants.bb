; @file constants.bb
;
; Some BlitzPlus constants to reduce memorization of arbitrary values.

; Bind event identifiers to constants so they are easier to remember.
Const EVENT_KEY_STROKE = $103
Const EVENT_GADGET_ACTION = $401
Const EVENT_WINDOW_RESIZED = $802
Const EVENT_WINDOW_CLOSE = $803
Const EVENT_MENU = $1001

; Used with CreateTextArea
Const TEXTAREA_NO_WORD_WRAP = 0
Const TEXTAREA_WORD_WRAP = 1

; Used with TextAreaCursor
Const TEXTAREA_CHARACTERS = 1
Const TEXTAREA_LINES = 2

; Return values of `Proceed()`
Const PROCEED_YES = 1
Const PROCEED_NO = 0
Const PROCEED_CANCEL = -1