Feature: Get secrets from Vault

  Scenario: Upload secrets to Vault
    Given I run 'curl -k -H "X-Vault-Token:stratio" -H "Content-Type:application/json" -X POST -d "{\"type\":\"generic\",\"description\":\"testing\"}" http://${VAULT_URL:-vault.demo.stratio.com}:8200/v1/sys/mounts/userland' locally with exit status '0'
    Then I run 'curl -k -H "X-Vault-Token:stratio" -H "Content-Type:application/json" -X POST -d "@src/test/resources/vault/test_cert.json" http://${VAULT_URL:-vault.demo.stratio.com}:8200/v1/userland/certificates/test' locally with exit status '0'
    And I run 'curl -k -H "X-Vault-Token:stratio" -H "Content-Type:application/json" -X POST -d "@src/test/resources/vault/test_keytab.json" http://${VAULT_URL:-vault.demo.stratio.com}:8200/v1/userland/kerberos/test' locally with exit status '0'

  Scenario: Get test crt
    Given I get '${TYPE:-crt}' from path '${PATH:-/userland/certificates/test}' for value '${VALUE:-test}' with token '${VAULT_TOKEN:-stratio}', unsecure vault host '${VAULT_URL:-vault.demo.stratio.com}' and save the value in environment variable 'test_crt'
    Then I run 'ls !{test_crt}' locally
    And I run 'cat !{test_crt} | grep BEGIN' locally
    And I run 'rm -f !{test_crt}' locally

  Scenario: Get test key
    Given I get '${TYPE:-key}' from path '${PATH:-/userland/certificates/test}' for value '${VALUE:-test}' with token '${VAULT_TOKEN:-stratio}', unsecure vault host '${VAULT_URL:-vault.demo.stratio.com}' and save the value in environment variable 'test_key'
    Then I run 'ls !{test_key}' locally
    And I run 'cat !{test_key} | grep BEGIN' locally
    And I run 'rm -f !{test_key}' locally

  Scenario: Get test.qa crt
    Given I get '${TYPE:-crt}' from path '${PATH:-/userland/certificates/test}' for value '${VALUE:-test.qa}' with token '${VAULT_TOKEN:-stratio}', unsecure vault host '${VAULT_URL:-vault.demo.stratio.com}' and save the value in environment variable 'test.qa_crt'
    Then I run 'ls !{test.qa_crt}' locally
    And I run 'cat !{test.qa_crt} | grep BEGIN' locally
    And I run 'rm -f !{test.qa_crt}' locally

  Scenario: Get test.qa key
    Given I get '${TYPE:-key}' from path '${PATH:-/userland/certificates/test}' for value '${VALUE:-test.qa}' with token '${VAULT_TOKEN:-stratio}', unsecure vault host '${VAULT_URL:-vault.demo.stratio.com}' and save the value in environment variable 'test.qa_key'
    Then I run 'ls !{test.qa_key}' locally
    And I run 'cat !{test.qa_key} | grep BEGIN' locally
    And I run 'rm -f !{test.qa_key}' locally

  Scenario: Get ca crt
    Given I get '${TYPE:-ca}' from path '${PATH:-/userland/certificates/test}' for value '${VALUE:-ca}' with token '${VAULT_TOKEN:-stratio}', unsecure vault host '${VAULT_URL:-vault.demo.stratio.com}' and save the value in environment variable 'ca_crt'
    Then I run 'ls !{ca_crt}' locally
    And I run 'cat !{ca_crt} | grep BEGIN' locally
    And I run 'rm -f !{ca_crt}' locally

  Scenario: Get test principal
    Given I get '${TYPE:-principal}' from path '${PATH:-/userland/kerberos/test}' for value '${VALUE:-test}' with token '${VAULT_TOKEN:-stratio}', unsecure vault host '${VAULT_URL:-vault.demo.stratio.com}' and save the value in environment variable 'test_principal'
    Then I run 'echo !{test_principal} | cut -d'@' -f1 | cut -d'/' -f1' locally and save the value in environment variable 'test_principal_content1'
    And I run 'echo !{test_principal} | cut -d'@' -f1 | cut -d'/' -f2' locally and save the value in environment variable 'test_principal_content2'

  Scenario: Get test keytab
    Given I get '${TYPE:-keytab}' from path '${PATH:-/userland/kerberos/test}' for value '${VALUE:-test}' with token '${VAULT_TOKEN:-stratio}', unsecure vault host '${VAULT_URL:-vault.demo.stratio.com}' and save the value in environment variable 'test_keytab_path'
    And I run 'strings !{test_keytab_path} | grep -vE "^(>|<)"' locally and save the value in environment variable 'test_keytab_content'
    And I run 'echo !{test_keytab_content} | grep ${REALM:-DEMO.STRATIO.COM}' locally
    And I run 'echo !{test_keytab_content} | grep !{test_principal_content1}' locally
    And I run 'echo !{test_keytab_content} | grep !{test_principal_content2}' locally
    And I run 'rm -f test.keytab' locally

  Scenario: Get test.qa principal
    Given I get '${TYPE:-principal}' from path '${PATH:-/userland/kerberos/test}' for value '${VALUE:-test.qa}' with token '${VAULT_TOKEN:-stratio}', unsecure vault host '${VAULT_URL:-vault.demo.stratio.com}' and save the value in environment variable 'test.qa_principal'
    Then I run 'echo !{test.qa_principal} | cut -d'@' -f1 | cut -d'/' -f1' locally and save the value in environment variable 'test.qa_principal_content1'
    And I run 'echo !{test.qa_principal} | cut -d'@' -f1 | cut -d'/' -f2' locally and save the value in environment variable 'test.qa_principal_content2'

  Scenario: Get test.qa keytab
    Given I get '${TYPE:-keytab}' from path '${PATH:-/userland/kerberos/test}' for value '${VALUE:-test.qa}' with token '${VAULT_TOKEN:-stratio}', unsecure vault host '${VAULT_URL:-vault.demo.stratio.com}' and save the value in environment variable 'test.qa_keytab_path'
    And I run 'strings !{test.qa_keytab_path}' locally and save the value in environment variable 'test.qa_keytab_content'
    And I run 'echo !{test.qa_keytab_content} | grep ${REALM:-DEMO.STRATIO.COM}' locally
    And I run 'echo !{test.qa_keytab_content} | grep !{test.qa_principal_content1}' locally
    And I run 'echo !{test.qa_keytab_content} | grep !{test.qa_principal_content2}' locally
    And I run 'rm -f test.qa.keytab' locally

  Scenario: Try to get secret with wrong path
    Given I get '${TYPE:-crt}' from path '${PATH:-/nonexistentpath}' for value '${VALUE:-testnonexistant}' with token '${TOKEN:-stratio}', unsecure vault host '${VAULT_URL:-vault.demo.stratio.com}' and save the value in environment variable 'non_existent_path'
    Then I run 'ls !{non_existent_path}' locally
    And I run 'cat !{non_existent_path} | grep BEGIN' locally with exit status '1'
    And I run 'rm -f testnonexistant.pem' locally
