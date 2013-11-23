; @file menu.bb
;
; Functions related to the `Menu` type.


Global global_id = 0

; `Menu` is a wrapper around BlitzPlus menus. In addition to the BlitzPlus menu
; object handle, it contains the identifier used for events. If made with
; `Create_Menu` then this ID will be unique, helping differentiate between
; different menus with ease.
Type Menu
	Field item ; The "reference" to the internal BlitzPlus menu.
	Field id% ; The unquie ID used for event detection
End Type

; Create a `Menu`.
; 
; @param txt The text which the menu displays
; @param parent The parent menu to attach this to. Please note that this is not
;               a `Menu` handle, but rather the BlitzPlus menu handle. Just pass
;               `Menu\item` and all will be fine.
; @return A valid `Menu` handle.
Function Menu_Create.Menu( txt$, parent )
	Local m.Menu = New Menu
	m\id% = global_id
	m\item = CreateMenu( txt$, m\id%, parent )
	global_id = global_id + 1
	Return m
End Function

; Free the allocated memory for the given menu.
;
; @TODO Why does FreeGadget not work with menus?
; @remark The given `Menu` handle will no longer be valid.
; @param m The `Menu` to free.
Function Menu_Free( m.Menu )
	Notify Str$( m\item ) ; DEBUG
	; FreeGadget m\item
	Delete m
End Function