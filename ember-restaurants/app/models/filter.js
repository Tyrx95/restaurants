import MainModel from 'ember-restaurants/models/main-model';

var _modelProps = ['pageNumber', 'itemsPerPage', 'searchText', 'priceRange','rating'];

export default MainModel.extend({
  modelProps : _modelProps,
});
