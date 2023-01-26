import sbt.util

logLevel := util.Level.Debug

addSbtPlugin("com.typesafe.sbt"  % "sbt-git"             % "0.9.3")
addSbtPlugin("com.eed3si9n"      % "sbt-assembly"        % "0.14.9")
addSbtPlugin("com.github.gseitz" % "sbt-release"         % "1.0.13")
addSbtPlugin("com.timushev.sbt"  % "sbt-updates"         % "0.5.0")
addSbtPlugin("com.codecommit"    % "sbt-github-packages" % "0.5.2")