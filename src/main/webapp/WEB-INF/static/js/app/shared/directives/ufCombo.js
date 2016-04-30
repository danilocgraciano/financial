var app = angular.module('ufCombo',[]);

app.directive('ufCombo', function() {
	return {
		restrict:'E',
		replace: true,
		template: '<select>'
      			+'<option value="">State</option>'
      			+'<option value="AC">Acre</option>'
      			+'<option value="AL">Alagoas</option>'
      			+'<option value="AP">Amap&aacute;</option>'
      			+'<option value="AM">Amazonas</option>'
      			+'<option value="BA">Bahia</option>'
      			+'<option value="CE">Ceara</option>'
      			+'<option value="DF">Distrito Federal</option>'
      			+'<option value="ES">Esp&iacute;rito Santo</option>'
      			+'<option value="GO">Goi&aacute;s</option>'
      			+'<option value="MA">Maranh&atilde;o</option>'
      			+'<option value="MT">Mato Grosso</option>'
      			+'<option value="MG">Minas Gerais</option>'
      			+'<option value="PA">Par&aacute;</option>'
      			+'<option value="PB">Para&iacute;ba</option>'
      			+'<option value="PR">Paran&aacute;</option>'
      			+'<option value="PE">Pernambuco</option>'
      			+'<option value="PI">Piau&iacute;</option>'
      			+'<option value="RJ">Rio de Janeiro</option>'
      			+'<option value="RN">Rio Gande do Norte</option>'
      			+'<option value="RS">Rio Gande do Sul</option>'
      			+'<option value="RO">Rond&ocirc;nia</option>'
      			+'<option value="RR">Roraima</option>'
      			+'<option value="SC">Santa Catarina</option>'
      			+'<option value="SP">S&atilde;o Paulo</option>'
      			+'<option value="SE">Segipe</option>'
      			+'<option value="TO">TOcantins</option>'
    		+'</select>'
	}
});