import AjaxService from 'ember-ajax/services/ajax';

export default AjaxService.extend({
  host: 'http://localhost:8080',
  contentType: 'application/json; charset=utf-8'
});
