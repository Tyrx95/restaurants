import DS from 'ember-data';

var _modelProperties = ['mark', 'comment']
export default DS.Model.extend({
  modelProps: _modelProperties,
});
