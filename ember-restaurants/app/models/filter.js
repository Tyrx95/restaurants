import MainModel from 'ember-restaurants/models/main-model';

var _modelProps = ['pageNumber', 'itemsPerPage', 'searchText'];

export default MainModel.extend({
  modelProps : _modelProps,
});
