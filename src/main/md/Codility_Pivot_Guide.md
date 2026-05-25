
# Codility Reporting Pivot Table Guide (Excel for Windows)

This guide walks through creating the key PivotTables and charts using the **Codility Data** sheet.

---

## 1. Convert Raw Data into an Excel Table

1. Open the `Codility Data` sheet.
2. Select the entire data range including headers.
3. Go to **Insert > Table**.
4. Ensure **My table has headers** is ticked.
5. Click **OK**.
6. In the Table Design ribbon, rename the table to:

```
tblCodility
```

---

## 2. Create Helper Columns (Attempted / Passed)

Since Excel cannot sum Yes/No text fields, convert them to numeric flags.

In `tblCodility`, add:

### AnyAttemptFlag
```
=IF([@[Any Belt Attempted]]="Yes",1,0)
```

### AnyPassedFlag
```
=IF([@[Any Belt Passed]]="Yes",1,0)
```

Excel auto-fills these for all rows.

---

## 3. Add a PassRate Column

Add another field:

### PassRate
```
=IF([@[AnyAttemptFlag]]=0,0,[@AnyPassedFlag]/[@AnyAttemptFlag])
```

Format this column as **Percentage (1 decimal)**.

---

## 4. Build the “YTD Codility – Active Engineers by Domain” Pivot Table

1. Click inside `tblCodility`.
2. Go to **Insert > PivotTable**.
3. Choose **From Table/Range** (important: *do not* tick “Add to Data Model”).
4. Place the PivotTable on a **new worksheet**.
5. Rename the sheet:

```
Rpt_YTD_Domain
```

### Pivot Setup

**Rows**
- `Domain`

**Values**
- `AnyAttemptFlag` → Sum → rename **Attempted**
- `AnyPassedFlag` → Sum → rename **Passed**
- `PassRate` → Average → rename **Pass Rate**

### Formatting
- Set Pass Rate column to Percentage (1 decimal)

---

## 5. Create the YTD Domain Chart

1. Select your PivotTable.
2. Go to **Insert > Combo Chart**.
3. Assign:
   - Attempted → Clustered Column
   - Passed → Clustered Column
   - Pass Rate → Line (Secondary Axis)
4. Add a chart title:

```
YTD Codility – Active Engineers by Domain
```

---

## 6. Build the “Belts Passed by Domain” Report

If needed, add helper columns:

```
WhitePassFlag   =IF([@[White belt Passed]]="Yes",1,0)
BeigePassFlag   =IF([@[Beige belt (SQL) Passed]]="Yes",1,0)
YellowPassFlag  =IF([@[Yellow Belt Passed]]="Yes",1,0)
BrownPassFlag   =IF([@[Brown Belt Passed]]="Yes",1,0)
BlackPassFlag   =IF([@[Black Belt Passed]]="Yes",1,0)
```

### Build the Pivot:

1. Insert another PivotTable.
2. Put it on a new sheet renamed:

```
Rpt_Belts_Passed
```

### Pivot Layout

**Rows**
- `Domain`

**Values**
- WhitePassFlag → Sum
- BeigePassFlag → Sum
- YellowPassFlag → Sum
- BrownPassFlag → Sum
- BlackPassFlag → Sum

Rename each field under Values to match the belt colour.

### Chart

1. Select the PivotTable.
2. Insert → **Stacked Column Chart**.
3. Title:

```
Active Engineers – Belts Passed
```

---

## 7. Build the “Gender Split – Passed” Report

1. Insert another PivotTable from `tblCodility`.
2. Place on:

```
Rpt_Gender
```

### Pivot Fields

**Filters**
- AnyPassedFlag → filter to **1** (passed only)

**Rows**
- Gender

**Values**
- Employee ID → Count → rename **Count of Employees**

### Pie Chart

1. Select the PivotTable.
2. Insert → **Pie Chart**.
3. Add Data Labels → **Percentage**.
4. Title:

```
Gender Split – Passed
```

---

## 8. Assemble the Dashboard (Optional)

1. Insert a new sheet called **Dashboard**.
2. Move or paste each chart into this sheet.
3. Arrange layout to match your reporting style:
   - Top Left: YTD Domain Chart
   - Bottom Left: Belts Passed Chart
   - Right Side: Gender Pie Chart
4. Add section headings if needed.

---

## 9. Monthly Refresh

To update:

1. Paste new data into `tblCodility`.
2. Right-click any PivotTable → **Refresh**.
3. Charts update automatically.

---

**End of Guide**
