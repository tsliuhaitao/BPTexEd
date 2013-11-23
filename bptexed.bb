; @file bptexed.bb
;
; The main file of the BPTexEd project, designed to make a text editor in
; BlitzPlus. Code is written in BlitzBasic. Comments sort of adhere to doxygen
; specifications.
;
; Since I haven't used BlitzPlus in a while, here are valid variable suffixes
; used to determine type.
; #			float
; %			int (default?)
; $			string
; .Type		an instance of Type
; (none)	used for object handles like the return value of CreateWindow

; Include external files
; Note: order is important! (e.g. cursor.bb relies on constants.bb)
Include "constants.bb"
Include "cursor.bb"
Include "menu.bb"

; Declare globals
Global window, textarea, menubar
Global font_courier
Global menu_file.Menu
Global cur.Cursor

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
	menu_file = Menu_Create( "File", menubar )
	UpdateWindowMenu window

	cur = Cursor_Create( textarea )
End Function

; Enter the main game loop.
;
; @remark Loop only stops when `EVENT_WINDOW_CLOSE` is encountered.
Function MainLoop()
	Repeat
		Local id = WaitEvent()
		Select id
			Case EVENT_GADGET_ACTION
				; Every time a key is pressed in a text area `EVENT_GADGET_ACTION` is fired.
				; However, `EventData()` returns 0 all the time.
				Cursor_Update cur
				SetStatusText window, "Col: " + Str$( cur\col% ) + ", Line: " + Str$( cur\lin% ) + ", Char: " + Str$( cur\char% )
			Case $801
				SetStatusText window,"Window Moved" ; DEBUG	
			Case EVENT_WINDOW_RESIZED
				SetStatusText window,"Window Sized" ; DEBUG
				ResizeWindowGadgets
			Case EVENT_WINDOW_CLOSE
				Notify "Received WindowClose Event" ; DEBUG
				Return
			Case $804
				SetStatusText window,"WindowActivate" ; DEBUG
			Case $805
				SetStatusText window,"WindowDeActivate" ; DEBUG
		End Select
	Forever
End Function

; Clean up all allocated resources.
Function CleanUp()
	Menu_Free menu_file
	FreeGadget textarea
	FreeGadget window
	FreeFont font_courier
	Cursor_Free cur
End Function

; Resize the gadgets to fit the client area of the main window.
Function ResizeWindowGadgets()
	Local w% = ClientWidth( window )
	Local h% = ClientHeight( window )
	SetGadgetShape textarea, 0, 0, w%, h%
End Function
