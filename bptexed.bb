; #		float
; %		int
; $		string

; Every time a key is pressed in a text area EVENT_GADGET_ACTION is fired.
; However, EventData returns 0 all the time.

; Set constants
; @TODO Move to another file?
Const EVENT_KEY_STROKE = $103
Const EVENT_GADGET_ACTION = $401
Const EVENT_WINDOW_RESIZED = $802
Const EVENT_WINDOW_CLOSE = $803

Const TEXTAREA_WORD_WRAP = 1
Const TEXTAREA_CHARACTERS = 1
Const TEXTAREA_LINES = 2

Type Cursor
	Field area ; the text area this is associated with
	Field char% ; the character that the cursor is at
	Field lin% ; the line number of the cursor
	Field col% ; the column of the cursor
End Type

Type Menu
	Field item ; the internal representation of the menu
	Field id% ; the ID used for event detection
End Type

; Declare globals
Global window, textarea, menubar
Global menu_file.Menu
Global font_courier
Global cur.Cursor
Global global_id

; Execute the main program
Initialize
MainLoop
CleanUp
End

; Initialize necessary variables.
Function Initialize()
	global_id = 0
	
	window = CreateWindow( "TexEd", 20, 20, 300, 200, 0, 15 )

	textarea = CreateTextArea( 0, 0, 200, 200, window, TEXTAREA_WORD_WRAP )
	font_courier = LoadFont( "Courier", 12 )
	SetTextAreaFont textarea, font_courier
	ResizeWindowGadgets

	menubar = WindowMenu( window )
	menu_file = MyCreateMenu( "File", menubar )
	UpdateWindowMenu window

	cur = New Cursor
	cur\char% = 0
	cur\lin% = 0
	cur\col% = 0
	cur\area = textarea
End Function

; Enter the main game loop.
;
; @remark Loop only stops when `EVENT_WINDOW_CLOSE` is encountered.
Function MainLoop()
	Repeat
		Local id = WaitEvent()
		Select id
			Case EVENT_GADGET_ACTION
				UpdateCursor( cur )
				SetStatusText window, "Col: " + Str$( cur\col% ) + ", Line: " + Str$( cur\lin% ) + ", Char: " + Str$( cur\char% )
			Case $801
				SetStatusText window,"Window Moved"		
			Case EVENT_WINDOW_RESIZED
				SetStatusText window,"Window Sized"
				ResizeWindowGadgets
			Case EVENT_WINDOW_CLOSE
				Notify "Received WindowClose Event"
				Return
			Case $804
				SetStatusText window,"WindowActivate"
			Case $805
				SetStatusText window,"WindowDeActivate"
		End Select
	Forever
End Function

; Clean up all allocated resources.
Function CleanUp()
	FreeMenu menu_file
	FreeGadget textarea
	FreeGadget window
	FreeFont font_courier
	Delete cur
End Function

; Resize the gadgets to fit the client area of the main window.
Function ResizeWindowGadgets()
	Local w% = ClientWidth( window )
	Local h% = ClientHeight( window )
	SetGadgetShape textarea, 0, 0, w%, h%
End Function

; Update the values of the given cursor.
;
; @param curs The `Cursor` to update.
Function UpdateCursor( curs.Cursor )
	curs\char% = TextAreaCursor%( curs\area, TEXTAREA_CHARACTERS )
	curs\lin% = TextAreaCursor%( curs\area, TEXTAREA_LINES )
	curs\col% = curs\char% - TextAreaChar%( curs\area, curs\lin% )
End Function

; Create a menu with a unique ID for event detection.
;
; @param txt The text which the menu displays
; @param parent The parent menu to attach this to
; @return A `Menu` with a unique ID for event detection
Function MyCreateMenu.Menu( txt$, parent )
	Local m.Menu = New Menu
	m\id% = global_id
	m\item = CreateMenu( txt$, m\id%, parent )
	global_id = global_id + 1
	Return m
End Function

; Free the allocated memory for the given menu.
;
; @TODO Why does FreeGadget not work with menus?
; @param m The `Menu` to free.
Function FreeMenu( m.Menu )
	Notify Str$( m\item )
	; FreeGadget m\item
	Delete m
End Function