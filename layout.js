// this function is in Vanilla JS
// 28 lines of code
function generateFormLayout(layout) {
  const pages = layout.pages;
  const languageId = layout.defaultLanguageId;
  pages.forEach((page) => {
    const titles = page.title;
    document.getElementById('title-div').appendChild(Object.assign(document.createElement('div'), { className: 'col-md-6', innerHTML: `Title ${titles.el_GR}` }));
    document.getElementById('title-div').appendChild(Object.assign(document.createElement('div'), { className: 'col-md-6', innerHTML: `Title ${titles[languageId]}` }));
    document.getElementById('title-div').appendChild(Object.assign(document.createElement('div'), { className: 'col-md-6', innerHTML: `Description ${page.description.el_GR}` }));
    document.getElementById('title-div').appendChild(Object.assign(document.createElement('div'), { className: 'col-md-6', innerHTML: `Description ${page.description[languageId]}` }));
    const rows = page.rows
    rows.forEach((row, index) => {
      const rowDiv = document.createElement('div');
      rowDiv.classList.add('row')
      const cols = row.columns;
      cols.forEach((col) => {
        let content = '';
        col.fieldNames.forEach((field) => {
          content += field
        })
        rowDiv.appendChild(Object.assign(document.createElement('div'), {
          className: `col-md-${col.size}`, innerHTML: `${content}`
        }));
        document.getElementById('form-l').appendChild(rowDiv);
      })
    })
  })
  document.getElementById('form-l').appendChild(Object.assign(document.createElement('div'), { className: 'row', innerHTML: `Pagination: ${layout.paginationMode}` }));
  document.getElementById('form-l').appendChild(Object.assign(document.createElement('div'), { className: 'row', innerHTML: `Language Id: ${layout.defaultLanguageId}` }));
}

//this function is the same as above in Jquery
//26 lines of code
function generateFormLayoutJquery(layout) {
  $(document).ready(function() {
    const pages = layout.pages;
    const languageId = layout.defaultLanguageId;
    pages.forEach((page) => {
      const titles = page.title;
      $('#title-div').append(`<div class="col-md-6"> ${titles[languageId]}`);
      $('#title-div').append(`<div class="col-md-6"> ${page.description[languageId]}`);
      $('#title-div').append(`<div class="col-md-6"> ${titles.el_GR}`);
      $('#title-div').append(`<div class="col-md-6"> ${page.description.en_US}`);
      const rows = page.rows
      rows.forEach((row, index) => {
        $("#form-l").append(`<div class="row" id="${index}" >`);
        const cols = row.columns;
        cols.forEach((col) => {
          let content = '';
          col.fieldNames.forEach((field) => {
            content += field
          })
          $(`#${index}`).append(`<div class="col-md-${col.size}"> ${content}`)
        })
      })
    })
    $('#form-l').append(`<div class="row"> ${layout.paginationMode}`)
    $('#form-l').append(`<div class="row"> ${layout.defaultLanguageId}`)
  });
}
