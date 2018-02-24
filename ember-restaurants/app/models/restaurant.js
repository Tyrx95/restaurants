import MainModel from 'ember-restaurants/models/main-model';

var _modelProperties = ['id', 'restaurantName', 'description', 'latitude', 'longitude',
  'mark', 'votes', 'priceRange', 'imageFileName', 'coverFileName', 'location',
  'foodType'
];
export default MainModel.extend({
  modelProps: _modelProperties,
});
