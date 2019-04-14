'use strict';

const testServerSpeech = () => {
    axios({
        method: 'GET',
        url: '/test'
    })
    .then(
        result => {
            console.log(result);
        }
    )
    .catch(
        error => {
            console.log(error);
        }
    );
};

angular.module('app').component('mainComponent',{
    templateUrl:'components/main-component/main-component.html',
    controller: function ($scope) {
        $scope.test = () => {
            testServerSpeech();
        }
    }
});


angular.module('app').directive('enterSubmit', function () {
    return {
      restrict: 'A',
      link: function (scope, elem, attrs) {       
        elem.bind('keydown', function(event) {
          var code = event.code;
          if (code === 'Enter' && this.value.length > 0) {
              event.preventDefault();
              axios({
                  method: 'POST',
                  url: '/text2speech',
                  data: {
                      textToConvert: this.value
                  }
              })
              .then(
                result => {
                    console.log(result);
                }
              )
              .catch(
                error => {
                    console.log(error);
                }
              );
          }
        });
      }
    }
  });
  