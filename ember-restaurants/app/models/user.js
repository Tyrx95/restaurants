import MainModel from 'ember-restaurants/models/main-model';

var _modelProperties = ['email','firstName','lastName','phone','country','city','password']

export default MainModel.extend({
  modelProps: _modelProperties
});
