Attribute VB_Name = "Module111"
Sub Sort()
Attribute Sort.VB_Description = "Macro recorded 3/28/2006 by bob"
Attribute Sort.VB_ProcData.VB_Invoke_Func = "s\n14"
'
' Sort Macro
' Macro recorded 3/28/2006 by bob
'
' Keyboard Shortcut: Ctrl+s
'
  For x = 1 To 8
Sheets(x).Select
    Range("A6:l65").Select
    Selection.Sort Key1:=Range("J6"), Order1:=xlDescending, Key2:=Range("E6") _
        , Order2:=xlAscending, Header:=xlGuess, OrderCustom:=1, MatchCase:=True _
        , Orientation:=xlTopToBottom, DataOption1:=xlSortNormal, DataOption2:= _
        xlSortNormal
    Range("D5").Select
    ActiveCell.FormulaR1C1 = "0"
    Range("D6").Select
    ActiveCell.FormulaR1C1 = "=IF(ISBLANK(RC[1]),"""",SUM(R[-1]C+1))"
    Range("D6").Select
    Selection.Copy
    Range("D7:D65").Select
    ActiveSheet.Paste
    Columns("D:D").Select
    Application.CutCopyMode = False
    Selection.Copy
    Application.CutCopyMode = False
    Selection.Copy
    Selection.PasteSpecial Paste:=xlPasteValues, Operation:=xlNone, SkipBlanks _
        :=False, Transpose:=False
    Range("D1").Select
    ActiveSheet.Paste
    Application.CutCopyMode = False
    Range("A1").Select
    ActiveCell.FormulaR1C1 = "=COUNTA(R[5]C[4]:R[54]C[4])"
    Range("A1").Select
    Selection.Copy
    Selection.PasteSpecial Paste:=xlPasteValues, Operation:=xlNone, SkipBlanks _
        :=False, Transpose:=False
    ActiveSheet.Paste
    Application.CutCopyMode = False
    Columns("B:D").Select
    With Selection
        .HorizontalAlignment = xlCenter
        .VerticalAlignment = xlBottom
        .WrapText = False
        .Orientation = 0
        .AddIndent = False
        .IndentLevel = 0
        .ShrinkToFit = False
        .ReadingOrder = xlContext
        .MergeCells = False
    End With
    Columns("D:D").Select
    Selection.Font.Bold = True
    Range("D6:J65").Select
    Selection.Borders(xlDiagonalDown).LineStyle = xlNone
    Selection.Borders(xlDiagonalUp).LineStyle = xlNone
    With Selection.Borders(xlEdgeLeft)
        .LineStyle = xlContinuous
        .Weight = xlMedium
        .ColorIndex = xlAutomatic
    End With
    With Selection.Borders(xlEdgeTop)
        .LineStyle = xlContinuous
        .Weight = xlMedium
        .ColorIndex = xlAutomatic
    End With
    With Selection.Borders(xlEdgeBottom)
        .LineStyle = xlContinuous
        .Weight = xlMedium
        .ColorIndex = xlAutomatic
    End With
    With Selection.Borders(xlEdgeRight)
        .LineStyle = xlContinuous
        .Weight = xlMedium
        .ColorIndex = xlAutomatic
    End With
    With Selection.Borders(xlInsideVertical)
        .LineStyle = xlContinuous
        .Weight = xlMedium
        .ColorIndex = xlAutomatic
    End With
    With Selection.Borders(xlInsideHorizontal)
        .LineStyle = xlContinuous
        .Weight = xlMedium
        .ColorIndex = xlAutomatic
    End With
    
    Range("A1").Select
    If ActiveCell.Value > 0 Then
        Range("D1:J41").Select
        ActiveSheet.PageSetup.PrintArea = "$D$1:$J$41"
        With ActiveSheet.PageSetup
            .LeftHeader = ""
            .CenterHeader = ""
            .RightHeader = ""
            .LeftFooter = ""
            .CenterFooter = ""
            .RightFooter = ""
            .LeftMargin = Application.InchesToPoints(0.5)
            .RightMargin = Application.InchesToPoints(0.25)
            .TopMargin = Application.InchesToPoints(0.1)
            .BottomMargin = Application.InchesToPoints(0.1)
            .HeaderMargin = Application.InchesToPoints(0.1)
            .FooterMargin = Application.InchesToPoints(0.1)
            .PrintHeadings = False
            .PrintGridlines = False
            .PrintComments = xlPrintNoComments
            .CenterHorizontally = False
            .CenterVertically = False
            .Orientation = xlPortrait
            .Draft = False
            .PaperSize = xlPaperA4
            .FirstPageNumber = xlAutomatic
            .Order = xlDownThenOver
            .BlackAndWhite = False
            .Zoom = 90
            .PrintErrors = xlPrintErrorsDisplayed
        End With
    End If
    Range("I6").Select
    ActiveCell.FormulaR1C1 = "=IF(ISBLANK(RC[-4]),"""",CONCATENATE(""_"",RC[4]))"
    Range("I6").Select
    Selection.Copy
    Range("I7:I66").Select
    ActiveSheet.Paste
    Application.CutCopyMode = False
    
    Range("K2").Select
 Next x
     Sheets("D1P1").Select
    Range("K2").Select
End Sub
Sub SignaturePrintRedDay()
Attribute SignaturePrintRedDay.VB_ProcData.VB_Invoke_Func = "r\n14"
'
' SignaturePrintRedDay Macro
' Macro recorded 3/29/2006 by SLCSD User
'
' Keyboard Shortcut: Ctrl+r
'
    For x = 1 To 4
        Sheets(x).Select
        Range("A1").Select
        If ActiveCell.Value > 0 Then
            Range("A1").Select
               If ActiveCell.Value > 37 Then
                  With ActiveSheet.PageSetup
                    .PrintTitleRows = "$1:$4"
                    .PrintTitleColumns = ""
                  End With
                  ActiveSheet.PageSetup.PrintArea = "$D$5:$J$65"
               End If
               If ActiveCell.Value <= 37 Then
                    ActiveSheet.PageSetup.PrintArea = "$D$1:$J$42"
               End If
               If ActiveCell.Value < 26 Then
                    ActiveSheet.PageSetup.PrintArea = "$D$1:$J$35"
               End If
               ActiveWindow.SelectedSheets.PrintOut Copies:=1, Collate:=True
        Else
           GoTo nextRead
        End If
nextRead:
    Next
    Sheets(1).Select
    Range("K2").Select
    Selection.ClearContents
    Sheets(5).Select
    Range("K2").Select
End Sub
Sub SignaturePrintBlackDay()
Attribute SignaturePrintBlackDay.VB_ProcData.VB_Invoke_Func = "b\n14"
'
' SignaturePrintRedDay Macro
' Macro recorded 3/29/2006 by SLCSD User
'
' Keyboard Shortcut: Ctrl+r
'
    For x = 5 To 8
        Sheets(x).Select
        Range("A1").Select
        
          If ActiveCell.Value > 0 Then
            Range("A1").Select
               If ActiveCell.Value > 37 Then
                  With ActiveSheet.PageSetup
                    .PrintTitleRows = "$1:$4"
                    .PrintTitleColumns = ""
                  End With
                  ActiveSheet.PageSetup.PrintArea = "$D$5:$J$65"
               End If
               If ActiveCell.Value <= 37 Then
                    ActiveSheet.PageSetup.PrintArea = "$D$1:$J$42"
               End If
               If ActiveCell.Value < 26 Then
                    ActiveSheet.PageSetup.PrintArea = "$D$1:$J$35"
               End If
               ActiveWindow.SelectedSheets.PrintOut Copies:=1, Collate:=True
        Else
          GoTo nextRead
        End If
nextRead:
    Next
    Sheets(5).Select
    Range("K2").Select
    
    Selection.ClearContents
    Sheets(1).Select
    Range("K2").Select
End Sub

Sub SortThisPage()
Attribute SortThisPage.VB_ProcData.VB_Invoke_Func = "t\n14"
'
' SortThisPage Macro
' Macro recorded 11/2/2006 by bob
'
' Keyboard Shortcut: Ctrl+t
'
    Range("A6:l65").Select
    Selection.Sort Key1:=Range("J6"), Order1:=xlDescending, Key2:=Range("E6") _
        , Order2:=xlAscending, Header:=xlGuess, OrderCustom:=1, MatchCase:=True _
        , Orientation:=xlTopToBottom, DataOption1:=xlSortNormal, DataOption2:= _
        xlSortNormal
    Range("D5").Select
    ActiveCell.FormulaR1C1 = "0"
    Range("D6").Select
    ActiveCell.FormulaR1C1 = "=IF(ISBLANK(RC[1]),"""",SUM(R[-1]C+1))"
    Range("D6").Select
    Selection.Copy
    Range("D7:D65").Select
    ActiveSheet.Paste
    Columns("D:D").Select
    Application.CutCopyMode = False
    Selection.Copy
    Application.CutCopyMode = False
    Selection.Copy
    Selection.PasteSpecial Paste:=xlPasteValues, Operation:=xlNone, SkipBlanks _
        :=False, Transpose:=False
    Range("D1").Select
    ActiveSheet.Paste
    Application.CutCopyMode = False
    Range("A1").Select
    ActiveCell.FormulaR1C1 = "=COUNTA(R[5]C[4]:R[54]C[4])"
    Range("A1").Select
    Selection.Copy
    Selection.PasteSpecial Paste:=xlPasteValues, Operation:=xlNone, SkipBlanks _
        :=False, Transpose:=False
    ActiveSheet.Paste
    Application.CutCopyMode = False
    Columns("B:D").Select
    With Selection
        .HorizontalAlignment = xlCenter
        .VerticalAlignment = xlBottom
        .WrapText = False
        .Orientation = 0
        .AddIndent = False
        .IndentLevel = 0
        .ShrinkToFit = False
        .ReadingOrder = xlContext
        .MergeCells = False
    End With
    Columns("D:D").Select
    Selection.Font.Bold = True
    Range("D6:J65").Select
    Selection.Borders(xlDiagonalDown).LineStyle = xlNone
    Selection.Borders(xlDiagonalUp).LineStyle = xlNone
    With Selection.Borders(xlEdgeLeft)
        .LineStyle = xlContinuous
        .Weight = xlMedium
        .ColorIndex = xlAutomatic
    End With
    With Selection.Borders(xlEdgeTop)
        .LineStyle = xlContinuous
        .Weight = xlMedium
        .ColorIndex = xlAutomatic
    End With
    With Selection.Borders(xlEdgeBottom)
        .LineStyle = xlContinuous
        .Weight = xlMedium
        .ColorIndex = xlAutomatic
    End With
    With Selection.Borders(xlEdgeRight)
        .LineStyle = xlContinuous
        .Weight = xlMedium
        .ColorIndex = xlAutomatic
    End With
    With Selection.Borders(xlInsideVertical)
        .LineStyle = xlContinuous
        .Weight = xlMedium
        .ColorIndex = xlAutomatic
    End With
    With Selection.Borders(xlInsideHorizontal)
        .LineStyle = xlContinuous
        .Weight = xlMedium
        .ColorIndex = xlAutomatic
    End With
    
    Range("A1").Select
    If ActiveCell.Value > 0 Then
        Range("D1:J41").Select
        ActiveSheet.PageSetup.PrintArea = "$D$1:$J$41"
        With ActiveSheet.PageSetup
            .LeftHeader = ""
            .CenterHeader = ""
            .RightHeader = ""
            .LeftFooter = ""
            .CenterFooter = ""
            .RightFooter = ""
            .LeftMargin = Application.InchesToPoints(0.5)
            .RightMargin = Application.InchesToPoints(0.25)
            .TopMargin = Application.InchesToPoints(0.1)
            .BottomMargin = Application.InchesToPoints(0.1)
            .HeaderMargin = Application.InchesToPoints(0.1)
            .FooterMargin = Application.InchesToPoints(0.1)
            .PrintHeadings = False
            .PrintGridlines = False
            .PrintComments = xlPrintNoComments
            .CenterHorizontally = False
            .CenterVertically = False
            .Orientation = xlPortrait
            .Draft = False
            .PaperSize = xlPaperA4
            .FirstPageNumber = xlAutomatic
            .Order = xlDownThenOver
            .BlackAndWhite = False
            .Zoom = 90
            .PrintErrors = xlPrintErrorsDisplayed
        End With
    End If
    Range("I6").Select
    ActiveCell.FormulaR1C1 = "=IF(ISBLANK(RC[-4]),"""",CONCATENATE(""_"",RC[4]))"
    Range("I6").Select
    Selection.Copy
    Range("I7:I66").Select
    ActiveSheet.Paste
    ActiveSheet.Paste
    Application.CutCopyMode = False
    Range("K2").Select

End Sub
Sub InitializeSheets()
'
' InitializeSheets Macro
' Macro recorded 3/28/2006 by bob
'
' Keyboard Shortcut: Ctrl+i
'
For x = 1 To 8
Sheets(x).Select
    Range("B5").Select
    With Selection
        .HorizontalAlignment = xlCenter
        .VerticalAlignment = xlBottom
        .WrapText = True
        .Orientation = 0
        .AddIndent = False
        .IndentLevel = 0
        .ShrinkToFit = False
        .ReadingOrder = xlContext
        .MergeCells = False
    End With
    Cells.Replace What:="zzz", Replacement:="=", LookAt:=xlPart, SearchOrder _
        :=xlByRows, MatchCase:=False, SearchFormat:=False, ReplaceFormat:=False
    Cells.Select
    Cells.EntireColumn.AutoFit
    Columns("D:H").Select
    Selection.ColumnWidth = 5
    Columns("B:B").Select
    Selection.ColumnWidth = 7
    Columns("E:E").Select
    Selection.ColumnWidth = 27
    Columns("J:J").Select
    Selection.ColumnWidth = 7
    Columns("I:I").Select
    Selection.ColumnWidth = 45
    Range("A5:J5").Select
    Range("J5").Activate
    Selection.Font.Bold = True
    With Selection
        .HorizontalAlignment = xlCenter
        .VerticalAlignment = xlBottom
        .WrapText = False
        .Orientation = 0
        .AddIndent = False
        .IndentLevel = 0
        .ShrinkToFit = False
        .ReadingOrder = xlContext
        .MergeCells = False
    End With
    Selection.Font.ColorIndex = 11
    Range("I1").Select
    Selection.Font.Bold = True
    Range("G1").Select
    Selection.Font.Bold = True
    Selection.Font.ColorIndex = 11

    Range("D5:J55").Select
    Selection.Borders(xlDiagonalDown).LineStyle = xlNone
    Selection.Borders(xlDiagonalUp).LineStyle = xlNone
    With Selection.Borders(xlEdgeLeft)
        .LineStyle = xlContinuous
        .Weight = xlMedium
        .ColorIndex = xlAutomatic
    End With
    With Selection.Borders(xlEdgeTop)
        .LineStyle = xlContinuous
        .Weight = xlMedium
        .ColorIndex = xlAutomatic
    End With
    With Selection.Borders(xlEdgeBottom)
        .LineStyle = xlContinuous
        .Weight = xlMedium
        .ColorIndex = xlAutomatic
    End With
    With Selection.Borders(xlEdgeRight)
        .LineStyle = xlContinuous
        .Weight = xlMedium
        .ColorIndex = xlAutomatic
    End With
    With Selection.Borders(xlInsideVertical)
        .LineStyle = xlContinuous
        .Weight = xlMedium
        .ColorIndex = xlAutomatic
    End With
    With Selection.Borders(xlInsideHorizontal)
        .LineStyle = xlContinuous
        .Weight = xlMedium
        .ColorIndex = xlAutomatic
    End With
    Rows("6:55").Select
    Selection.RowHeight = 23

    Range("K2").Select
 If x = 1 Or x = 5 Then
    
    Columns("K:K").ColumnWidth = 14
    Selection.Borders(xlDiagonalDown).LineStyle = xlNone
    Selection.Borders(xlDiagonalUp).LineStyle = xlNone
    With Selection.Borders(xlEdgeLeft)
        .LineStyle = xlContinuous
        .Weight = xlMedium
        .ColorIndex = xlAutomatic
    End With
    With Selection.Borders(xlEdgeTop)
        .LineStyle = xlContinuous
        .Weight = xlMedium
        .ColorIndex = xlAutomatic
    End With
    With Selection.Borders(xlEdgeBottom)
        .LineStyle = xlContinuous
        .Weight = xlMedium
        .ColorIndex = xlAutomatic
    End With
    With Selection.Borders(xlEdgeRight)
        .LineStyle = xlContinuous
        .Weight = xlMedium
        .ColorIndex = xlAutomatic
    End With
    With Selection.Interior
        .ColorIndex = 34
        .Pattern = xlSolid
        .PatternColorIndex = xlAutomatic
    End With
      
    Range("K3").Select
    ActiveCell.FormulaR1C1 = "^Enter a different Date"
    Range("K3").Select
    Selection.Font.ColorIndex = 3
     Columns("D:D").Select
    With Selection
        .HorizontalAlignment = xlCenter
        .VerticalAlignment = xlBottom
        .WrapText = False
        .Orientation = 0
        .AddIndent = False
        .IndentLevel = 0
        .ShrinkToFit = False
        .ReadingOrder = xlContext
        .MergeCells = False
    End With
    Selection.Font.Bold = True
    Columns("B:C").Select
    With Selection
        .HorizontalAlignment = xlCenter
        .VerticalAlignment = xlBottom
        .WrapText = False
        .Orientation = 0
        .AddIndent = False
        .IndentLevel = 0
        .ShrinkToFit = False
        .ReadingOrder = xlContext
        .MergeCells = False
    End With
    End If
    
        Range("E6").Select
    Range("B5").Select
    With Selection
        .HorizontalAlignment = xlCenter
        .VerticalAlignment = xlBottom
        .WrapText = True
        .Orientation = 0
        .AddIndent = False
        .IndentLevel = 0
        .ShrinkToFit = False
        .ReadingOrder = xlContext
        .MergeCells = False
    End With
 
 Next x
    Sheets("D1P1").Select
    Range("K2").Select
    ActiveWorkbook.Save
End Sub
