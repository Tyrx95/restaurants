import MainModel from 'ember-restaurants/models/main-model';

var _modelProperties = ['name', 'description','price','type','idRestaurant'];

export default MainModel.extend({
	modelProps: _modelProperties,
});
