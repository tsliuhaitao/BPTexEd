; @file cursor.bb
;
; Types and functions related to the `Cursor` type.

; A `Cursor` contains information related to where the cursor is an associated
; BlitzPlus text area. This information includes line number, column number,
; etc.
Type Cursor
	Field area ; a "weak reference" to the associated text area
	Field char% ; the character that the cursor is at
	Field lin% ; the line number of the cursor
	Field col% ; the column of the cursor
End Type

; Create a cursor associated with a text area.
;
; @param textarea The text area to associate this cursor with.
; @return A valid `Cursor` handle.
Function Cursor_Create.Cursor( textarea )
	Local c.Cursor = New Cursor
	c\area = textarea
	Cursor_Update c
	Return c
End Function

; Update the values of the given cursor.
;
; @param curs The `Cursor` to update.
Function Cursor_Update( c.Cursor )
	c\char% = TextAreaCursor%( c\area, TEXTAREA_CHARACTERS )
	c\lin% = TextAreaCursor%( c\area, TEXTAREA_LINES )
	c\col% = c\char% - TextAreaChar%( c\area, c\lin% )
End Function

; Free the memory associated with the given cursor.
;
; @remark The given handle will no longer be valid. Does not touch the `area`
;         field.
; @param c The handle of the `Cursor` to free.
Function Cursor_Free( c.Cursor )
	Delete c
End Function