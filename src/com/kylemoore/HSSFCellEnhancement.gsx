package com.kylemoore

uses org.apache.poi.hssf.usermodel.HSSFCell

enhancement HSSFCellEnhancement : HSSFCell {

  property get isString() : boolean {
    return this.CellType == HSSFCell.CELL_TYPE_STRING ? true : false
  }

}
