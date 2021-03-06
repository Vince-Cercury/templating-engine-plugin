/*
   Copyright 2018 Booz Allen Hamilton

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

package org.boozallen.plugins.jte.config.libraries

import org.boozallen.plugins.jte.utils.FileSystemWrapper
import org.boozallen.plugins.jte.console.TemplateLogger
import org.boozallen.plugins.jte.binding.injectors.LibraryLoader
import org.kohsuke.stapler.DataBoundConstructor
import org.kohsuke.stapler.DataBoundSetter
import hudson.scm.SCM
import jenkins.scm.api.SCMFileSystem
import jenkins.scm.api.SCMFile 
import hudson.Extension
import hudson.model.AbstractDescribableImpl
import hudson.model.Descriptor
import hudson.Util
import org.jenkinsci.plugins.workflow.cps.CpsScript
import hudson.ExtensionPoint
import hudson.ExtensionList 
import jenkins.model.Jenkins 
import hudson.model.DescriptorVisibilityFilter


public class LibraryConfiguration extends AbstractDescribableImpl<LibraryConfiguration> {
    private final LibraryProvider libraryProvider

    @DataBoundConstructor public LibraryConfiguration(LibraryProvider libraryProvider) {
        this.libraryProvider = libraryProvider;
    }

    public LibraryProvider getLibraryProvider(){ return libraryProvider }

    @Extension public static class DescriptorImpl extends Descriptor<LibraryConfiguration> {
        public static List<LibraryProvider.LibraryProviderDescriptor> getLibraryProviders(){
            return DescriptorVisibilityFilter.apply(null, Jenkins.get().getExtensionList(LibraryProvider.LibraryProviderDescriptor))
        }
    }
}
